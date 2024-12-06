package org.example.controllers;

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
        this.commands.putIfAbsent("exit", new ExitCommand(this.controller));
        this.commands.putIfAbsent("login", new LoginCommand(this.controller));
        this.commands.putIfAbsent("logout", new LogoutCommand(this.controller));
        this.commands.putIfAbsent("player-create", new PlayerCreateCommand(this.controller));
        this.commands.putIfAbsent("player-delete", new PlayerDeleteCommand(this.controller));
        this.commands.putIfAbsent("statistics-show", new StatisticsShowCommand(this.controller));
        this.commands.putIfAbsent("team-add", new TeamAddCommand(this.controller));
        this.commands.putIfAbsent("team-create", new TeamCreateCommand(this.controller));
        this.commands.putIfAbsent("team-delete", new TeamDeleteCommand(this.controller));
        this.commands.putIfAbsent("team-remove", new TeamRemoveCommand(this.controller));
        this.commands.putIfAbsent("tournament-add", new TournamentAddCommand(this.controller));
        this.commands.putIfAbsent("tournament-create", new TournamentCreateCommand(this.controller));
        this.commands.putIfAbsent("tournament-delete", new TournamentDeleteCommand(this.controller));
        this.commands.putIfAbsent("tournament-list", new TournamentListCommand(this.controller));
        this.commands.putIfAbsent("tournament-matchmaking", new TournamentMatchmakingCommand(this.controller));
        this.commands.putIfAbsent("tournament-remove", new TournamentRemoveCommand(this.controller));
    }
}
