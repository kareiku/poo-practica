package org.example.models;

public enum Message {
    WELCOME("Welcome to the Sport's Management System.\n" +
            "Write a command to start using the app.\n" +
            "If unsure, use the \"help\" command."),
    INPUT_LINE(":~$ "),
    BYE("Exiting the application...");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
