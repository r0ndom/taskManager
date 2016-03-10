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
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.pb.task.manager.dao.UserDao;
import com.pb.task.manager.model.User;
import com.pb.task.manager.service.security.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
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
            Arrays.asList(DriveScopes.DRIVE_FILE);

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

    private Drive getDriveService(Long id) throws IOException {
        Credential credential = authorize(id);
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public void createFile() throws IOException {
        User user = userDao.getCurrentUser();
        Drive drive = getDriveService(user.getId());
        //drive.files().create()
    }

}
