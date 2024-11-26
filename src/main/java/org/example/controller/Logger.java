package org.example.controller;

import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class Logger {
    private final PrintWriter writer;

    public Logger(String directory) throws IOException {
        final String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss"));
        this.writer = new PrintWriter(String.format("%s/%s.log", directory, time));
    }

    public void log(String line) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.writer.printf("[%s]\t%s\n", time, line);
    }

    public void close() {
        this.writer.close();
    }
}
