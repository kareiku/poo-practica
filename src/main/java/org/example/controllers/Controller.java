package org.example.controllers;

import org.example.models.*;
import org.example.models.Error;
import org.example.views.commands.Command;
import org.example.views.commands.ExitCommand;

import java.util.Map;

public class Controller {
    private final User currentUser;
    private final UserSet users;
    private final ParticipantSet participants;
    private final TournamentList tournaments;
    private Map<String, Command> commands;

    public Controller() {
        this.currentUser = new User(Role.GUEST);
        this.users = new DatabaseReader().loadUsers();
        this.participants = new DatabaseReader().loadParticipants();
        this.tournaments = new DatabaseReader().loadTournaments();
        this.commands = new CommandBuilder().buildCommands();
    }

    public Error handleInput(String statement) {
        statement = statement.toLowerCase();
        if (statement.isEmpty()) {
            return Error.EMPTY_STATEMENT;
        }
        String commandName = statement.split("\\s+", 2)[0];
        String[] args = statement.split("\\s+", 2)[1].split(";");
        Command command = commands.get(commandName);
        if (command != null) {
            if (new PermissionChecker().hasPermission(command, currentUser.getRole())) {
                return command.execute(args);
            }
            return Error.NO_PERMISSION;
        }
        return Error.COMMAND_NOT_FOUND;
    }

    public boolean exitHasBeenExecuted() {
        return ((ExitCommand) commands.get("exit")).hasBeenExecuted();
    }
}
