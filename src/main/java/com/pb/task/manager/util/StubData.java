package com.pb.task.manager.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 12/31/2015.
 */
public class StubData {
    public static List<String> getAuthors() {
        List<String> list = new ArrayList<String>();
        list.add("ALL");
        list.add("Makarenko");
        list.add("Unchenko");
        list.add("Romnova");
        return list;
    }

    public static List<String> getExecutors() {
        List<String> list = new ArrayList<String>();
        list.add("ALL");
        list.add("Berdnik");
        list.add("Mednikov");
        list.add("Stesenko");
        return list;
    }

    public static List<String> getStatuses() {
        List<String> list = new ArrayList<String>();
        list.add("ALL");
        list.add("NEW");
        list.add("DONE");
        list.add("DEVELOPING");
        list.add("TESTING");
        return list;
    }
}
