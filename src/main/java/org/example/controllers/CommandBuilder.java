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
        this.addCommand(new PlayerCreateCommand("player-create", "<DNI>;<name>;<surnames>", "Creates a player in the system."));
        this.addCommand(new PlayerDeleteCommand("player-delete", "<DNI>", "Removes a player from the system."));
        this.addCommand(new StatisticsShowCommand("statistics-show", "", "Shows the statistics of the logged player or their team, in their case."));
        this.addCommand(new TeamAddCommand("team-add", "<DNI>;<team name>", "Adds the specified player to the specified team."));
        this.addCommand(new TeamCreateCommand("team-create", "<name>", "Adds a team to the database."));
        this.addCommand(new TeamDeleteCommand("team-delete", "<name>", "Removes a team from the database."));
        this.addCommand(new TeamRemoveCommand("team-remove", "<DNI>", "Removes the specified player from the specified team."));
        this.addCommand(new TournamentAddCommand());
        this.addCommand(new TournamentCreateCommand());
        this.addCommand(new TournamentDeleteCommand());
        this.addCommand(new TournamentListCommand());
        this.addCommand(new TournamentMatchmakingCommand());
        this.addCommand(new TournamentRemoveCommand());
    }
}
