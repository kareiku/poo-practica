package org.example.controllers;

public class CommandController {
    // fixme (down) Same as in CLI.
    private final Authenticator authenticator = new Authenticator();

    // fixme (please)
    public void handleInput(String statement) {
        String command = statement.split("\\s+")[0];
        command.matches(switch (dest)
        case "login" -> authenticator.login();
        )
        if (command.matches("login|logout")) {
            command.matches("login") ->
        } else {
        }
    }

    public boolean exitHasBeenExecuted() {
        return false;
    }

    private void getCommand() {
    }

    private void runCommand() {
    }

    private void delegate() {
    }

    private void decodeInput() {
    }
}
