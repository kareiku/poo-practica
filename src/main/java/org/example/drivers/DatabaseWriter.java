package org.example.drivers;

public class DatabaseWriter {
    private final String table;

    public DatabaseWriter(String table) {
        this.table = table;
    }

    public void create(String[] data) {
        if (data.length < new DatabaseReader(table).fieldCount()) {
            System.err.println("Error: can't create a new player due to the number of parameters passed.");
        } else {
            // TODO
        }
    }
}
