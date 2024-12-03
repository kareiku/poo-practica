package org.example.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Database {
    private static final String path = "../../../../resources/schemas/sportsmanager/";
    private final String table;

    public Database(String table) {
        this.table = table;
    }

    public Deque<String> loadData() {
        Deque<String> data = new ArrayDeque<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path + this.table));
            String line;
            while ((line = reader.readLine()) != null) {
                data.push(line);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
        return data;
    }

    public void storeData(String line) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path + this.table, true))) {
            writer.println(line);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void removeData(String line) {
        Deque<String> data = loadData();
        data.remove(line);
        this.emptyTable();
        while (!data.isEmpty()) {
            this.storeData(data.pop());
        }
    }

    private void emptyTable() {
        try (FileWriter writer = new FileWriter(path + this.table)) {
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
