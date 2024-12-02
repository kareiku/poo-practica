package org.example.controllers;

import org.example.commands.Command;
import org.example.models.Error;

import java.util.Map;

public class CommandController {
    // fixme (down) Same as in CLI.
    private final Authenticator authenticator = new Authenticator();
    private final Map<String, Command> commands = new CommandBuilder().buildCommands();

    public Error handleInput(String statement) {
        if (statement.isEmpty()) {
            return Error.EMPTY_STATEMENT;
        }
        Error error = Error.NONE;
        String[] parts = statement.split("\\s+", 2);
        String commandToRunName = parts[0];
        String[] args = parts[1].split(";");
        if (commandToRunName.equals("login")) {
            error = this.authenticator.login(args[0], args[1]);
        } else if (commandToRunName.equals("logout")) {
            error = this.authenticator.logout();
        } else {
            commands.forEach((commandName, command) -> {
            });
        }
        return error;
    }

    public boolean exitHasBeenExecuted() {
        return false;
    }
}
