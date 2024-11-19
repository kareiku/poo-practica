package org.example.views;

public enum Error {
    UNKNOWN_COMMAND_ERROR("Error: unkown command."),
    FORMAT_ERROR("Error: incorrect command format."),
    PARSE_ERROR("Error: there was a problem while parsing values."),
    NO_PERMISSION("Error: you don't have permission to run that command.");

    private final String message;

    Error(String message) {
        this.message = message;
    }

    public void write() {
        System.out.println(message);;
    }
}
