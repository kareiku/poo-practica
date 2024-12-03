package org.example.controllers;

import org.example.views.commands.Command;
import org.example.models.Error;
import org.example.models.Role;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    private final Authenticator authenticator;
    private final Map<Role, Map<String, Command>> commandsByRole;

    public Controller() {
        this.authenticator = new Authenticator();
        this.commandsByRole = new HashMap<>();
        Map<String, Command> allCommands = new CommandBuilder().buildCommands();
        this.commandsByRole.put(Role.ADMIN, null); // fixme
    }

    public Error handleInput(String statement) {
        if (statement.isEmpty()) {
            return Error.EMPTY_STATEMENT;
        }
        String[] parts = statement.split("\\s+", 2);
        String commandToRunName = parts[0];
        String[] args = parts[1].split(";");
        if (commandToRunName.equals("login")) {
            return this.authenticator.login(args[0], args[1]);
        } else if (commandToRunName.equals("logout")) {
            return this.authenticator.logout();
        } else {
            Command commandToRun = null;
            for (String commandName : commands.keySet()) {
                if (commandToRunName.equals(commandName)) {
                    commandToRun = commands.get(commandName);
                }
            }
            if (commandToRun == null) {
                return Error.COMMAND_NOT_FOUND;
            } else {
                return commandToRun.execute(args);
            }
        }
    }

    public boolean exitHasBeenExecuted() {
        return false;
    }
}
