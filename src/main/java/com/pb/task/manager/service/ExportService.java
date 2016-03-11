package com.pb.task.manager.service;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.gdata.client.spreadsheet.SpreadsheetQuery;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.ServiceException;
import com.pb.task.manager.dao.UserDao;
import com.pb.task.manager.model.GenerateData;
import com.pb.task.manager.model.TaskData;
import com.pb.task.manager.model.User;
import com.pb.task.manager.service.security.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SocketUtils;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by Mednikov on 09.03.2016.
 */
@Service
public class ExportService {

    @Autowired
    private TokenHandler handler;

    private static final String APPLICATION_NAME = "taskmanager-1238";

    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/drive-java-quickstart.json");

    private static FileDataStoreFactory DATA_STORE_FACTORY;

    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    private static HttpTransport HTTP_TRANSPORT;

    private static final List<String> SCOPES =
            Arrays.asList(DriveScopes.DRIVE, "https://spreadsheets.google.com/feeds/");

    private static final String viewUrl = "https://docs.google.com/spreadsheets/d/";

    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void init() {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private Credential authorize(Long id) throws IOException {

        InputStream in = ExportService.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = flow.loadCredential(String.valueOf(id));
        if (credential != null
                && (credential.getRefreshToken() != null || credential.getExpiresInSeconds() > 60)) {
            return credential;
        }
        String redirectUri = "http://localhost:8080/auth";
        AuthorizationCodeRequestUrl authorizationUrl =
                flow.newAuthorizationUrl().setRedirectUri(redirectUri);
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    System.out.println("Attempting to open that address in the default browser now...");
                    desktop.browse(URI.create(authorizationUrl.build()));
                }
            }
        } catch (IOException e) {
        } catch (InternalError e) {

        }
        String code = handler.getToken();
        TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectUri).execute();
        return flow.createAndStoreCredential(response, String.valueOf(id));
    }

    private Drive getDriveService(Credential credential) throws IOException {

        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private GenerateData createFile(Credential credential) throws IOException {
        String name = "report" + new Date();
        Drive drive = getDriveService(credential);
        File fileMetadata = new File();
        fileMetadata.setName(name);
        fileMetadata.setMimeType("application/vnd.google-apps.spreadsheet");
        java.io.File mediaFile = new java.io.File("D:\\1.xlsx");
        InputStreamContent mediaContent =
                new InputStreamContent("application/vnd.ms-excel",
                        new BufferedInputStream(new FileInputStream(mediaFile)));
        mediaContent.setLength(mediaFile.length());
        File response = drive.files().create(fileMetadata, mediaContent).setFields("id").execute();
        System.out.println("Response id:" + response.getId());
        GenerateData data = new GenerateData();
        data.setId(response.getId());
        data.setName(name);
        return data;
    }

    private String redirectUrl(String id) {
        return viewUrl + id + "/edit";
    }

    public String generateReport(List<TaskData> data) throws IOException, ServiceException {
        User user = userDao.getCurrentUser();
        Credential credential = authorize(user.getId());
        GenerateData generateData = createFile(credential);
        String id = generateData.getId();
        SpreadsheetService service =
                new SpreadsheetService("MySpreadsheetIntegration");
        service.setProtocolVersion(SpreadsheetService.Versions.V3);
        service.setOAuth2Credentials(credential);

        SpreadsheetEntry spreadsheet = getSpreadsheet(service, generateData.getName());
        System.out.println(spreadsheet.getTitle().getPlainText());
        WorksheetFeed worksheetFeed = service.getFeed(
                spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
        List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
        WorksheetEntry worksheet = worksheets.get(0);

        URL listFeedUrl = worksheet.getListFeedUrl();
        ListFeed listFeed = service.getFeed(listFeedUrl, ListFeed.class);

        // Send the new row to the API for insertion.
        for (TaskData td : data) {
            Map<String, String> rowValues = td.getParams();
            ListEntry row = createRow(rowValues);
            //service.insert(listFeedUrl, row);
            listFeed.insert(row);
        }
        return redirectUrl(id);
    }

    private ListEntry createRow(Map<String, String> rowValues) {
        ListEntry row = new ListEntry();
        for (String columnName : rowValues.keySet()) {
            String value = rowValues.get(columnName);
            row.getCustomElements().setValueLocal(columnName,
                    value);
        }
        return row;
    }

    private SpreadsheetEntry getSpreadsheet(SpreadsheetService service, String sheetName) throws IllegalStateException{
        try {

            SpreadsheetQuery spreadsheetQuery = new SpreadsheetQuery(
                    getSheetsServiceUrl());
            spreadsheetQuery.setTitleQuery(sheetName);
            spreadsheetQuery.setTitleExact(true);
            SpreadsheetFeed spreadsheet = service.getFeed(spreadsheetQuery,
                    SpreadsheetFeed.class);

            if (spreadsheet.getEntries() != null
                    && spreadsheet.getEntries().size() == 1) {
                return spreadsheet.getEntries().get(0);
            } else {
                throw new IllegalStateException();
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public URL getSheetsServiceUrl() {
        URL SPREADSHEET_FEED_URL = null;
        try {
            SPREADSHEET_FEED_URL = new URL(
                    "https://spreadsheets.google.com/feeds/spreadsheets/private/full");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return SPREADSHEET_FEED_URL;
    }
}
