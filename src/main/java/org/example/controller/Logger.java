package org.example.controller;

import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss"));
    private final PrintWriter writer;

    public Logger(String directory) throws IOException {
        writer = new PrintWriter(String.format("%s/%s.log", directory, time));
    }

    public void log(String line) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.writer.printf("[%s]\t%s\n", time, line);
    }
}
