package org.example.controllers;

import org.example.views.commands.Command;
import org.example.models.Error;
import org.example.models.Role;
import org.example.views.commands.ExitCommand;

import java.util.Map;

public class Controller {
    private final UserSet users;
    private final ParticipantSet participants;
    private final TournamentSet tournaments;
    private final Map<Role, Map<String, Command>> commandMap;
    private Map<String, Command> availableCommandsMap;

    public Controller() {
        this.authenticator = new Authenticator();
        this.commandMap = new CommandBuilder().buildCommands();
    }

    public Error handleInput(String statement) {
        this.availableCommandsMap = commandMap.get(this.authenticator.currentUserRole());
        statement = statement.toLowerCase();
        if (statement.isEmpty()) {
            return Error.EMPTY_STATEMENT;
        }
        String commandName = statement.split("\\s+", 2)[0];
        String[] args = statement.split("\\s+", 2)[1].split(";");
        Command command = availableCommandsMap.get(commandName);
        if (command != null) {
            return command.execute(args);
        }
        return Error.COMMAND_NOT_FOUND;
    }

    public boolean exitHasBeenExecuted() {
        return ((ExitCommand) availableCommandsMap.get("exit")).hasBeenExecuted();
    }
}
