package org.example.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseReader extends BufferedReader {
    public DatabaseReader(String path) throws IOException {
        super(new FileReader(path));
    }
}
