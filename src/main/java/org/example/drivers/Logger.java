package org.example.drivers;

import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger extends PrintWriter {
    private static final String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss"));

    public Logger(String directory) throws IOException {
        super(new PrintWriter(String.format("%s/%s.log", directory, time)));
    }

    public void log(String line) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.printf("[%s]\t%s\n", time, line);
    }
}
