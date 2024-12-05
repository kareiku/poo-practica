package org.example.services;

import org.example.views.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Controller controller;
    private final Map<String, Command> commands;

    public CommandFactory() {
        this.controller = new Controller();
        this.commands = new HashMap<>();
        this.createCommands();
    }

    public Command getCommand(String commandName) {
        return this.commands.get(commandName);
    }

    private void createCommands() {
        this.commands.putIfAbsent("login", new LoginCommand(controller));
        this.commands.putIfAbsent("logout", new LogoutCommand(controller));
        this.commands.putIfAbsent("player-create", new PlayerCreateCommand(controller));
        this.commands.putIfAbsent("player-delete", new PlayerDeleteCommand(controller));
        this.commands.putIfAbsent("statistics-show", new StatisticsShowCommand(controller));
        this.commands.putIfAbsent("team-add", new TeamAddCommand(controller));
        this.commands.putIfAbsent("team-create", new TeamCreateCommand(controller));
        this.commands.putIfAbsent("team-delete", new TeamDeleteCommand(controller));
        this.commands.putIfAbsent("team-remove", new TeamRemoveCommand(controller));
        this.commands.putIfAbsent("tournament-add", new TournamentAddCommand(controller));
        this.commands.putIfAbsent("tournament-create", new TournamentCreateCommand(controller));
        this.commands.putIfAbsent("tournament-delete", new TournamentDeleteCommand(controller));
        this.commands.putIfAbsent("tournament-list", new TournamentListCommand(controller));
        this.commands.putIfAbsent("tournament-matchmaking", new TournamentMatchmakingCommand(controller));
        this.commands.putIfAbsent("tournament-remove", new TournamentRemoveCommand(controller));
    }
}
