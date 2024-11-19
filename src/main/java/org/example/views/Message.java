package org.example.views;

public enum Message {
    WELCOME("Welcome to the Sport's Management System.\nWrite a command to start managing your players."),
    INPUT_LINE("> "),
    BYE("Exiting the application...");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public void write() {
        System.out.println(message);
    }
}
