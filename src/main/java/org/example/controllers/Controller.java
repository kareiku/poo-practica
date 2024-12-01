package org.example.controllers;

public class Controller {
    // fixme (down) Same as in CLI.
    private final AuthController authController = new AuthController();
    private final CommandController commandController = new CommandController();
    private final DatabaseController databaseController = new DatabaseController();

    // fixme (please)
    public void handleInput(String statement) {
        String command = statement.split("\\s+")[0];
        command.matches(switch (dest)
        case "login" -> authController.login();
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
