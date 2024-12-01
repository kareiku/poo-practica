package org.example.controllers;

import org.example.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandBuilder {
    private final Map<String, Command> commandMap;

    public CommandBuilder() {
        this.commandMap = new HashMap<>();
        this.addCommands();
    }

    public Map<String, Command> getCommandMap() {
        return this.commandMap;
    }

    private void addCommand(Command command) {
        this.commandMap.putIfAbsent(command.getName(), command);
    }

    private void addCommands() {
        this.addCommand(new ExitCommand("exit", "", "Saves changes and exits the application."));
        this.addCommand(new LoginCommand());
        this.addCommand(new LogoutCommand());
        this.addCommand(new PlayerCreateCommand("player-create", "<DNI>;<name>;<surnames>", "Creates a player in the system."));
        this.addCommand(new PlayerDeleteCommand("player-delete", "<DNI>", "Removes a player from the system."));
        this.addCommand(new StatisticsShowCommand());
        this.addCommand(new TeamAddCommand());
        this.addCommand(new TeamCreateCommand());
        this.addCommand(new TeamDeleteCommand());
        this.addCommand(new TeamRemoveCommand());
        this.addCommand(new TournamentAddCommand());
        this.addCommand(new TournamentCreateCommand());
        this.addCommand(new TournamentDeleteCommand());
        this.addCommand(new TournamentListCommand());
        this.addCommand(new TournamentMatchmakingCommand());
        this.addCommand(new TournamentRemoveCommand());
    }
}
