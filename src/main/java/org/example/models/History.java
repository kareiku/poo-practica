package org.example.models;

import java.io.PrintWriter;
import java.io.IOException;

// fixme! This opens the file and closes it immediatly instead of keeping it open and closing it when the process finishes
//     It should, instead:
//     1. open it
//     2. let it be used to write
//     3. close it
public class History {
    private PrintWriter historyFile;

    public History(String path) {
        this.historyFile = null;
        try {
            historyFile = new PrintWriter(path);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (historyFile != null) {
                historyFile.close();
            }
        }
    }

    public void save(String line) {
        historyFile.println(line);
    }
}
