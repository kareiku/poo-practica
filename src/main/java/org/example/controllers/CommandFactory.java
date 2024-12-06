package org.example.controllers;

import org.example.views.commands.*;

import java.util.HashMap;

public class CommandFactory {
    private final Controller controller;
    private final HashMap<String, Command> commands;

    public CommandFactory() {
        this.controller = new Controller();
        this.commands = new HashMap<>();
        this.createCommands();
        this.ensureNull();
    }

    public Command getCommand(String commandName) {
        return this.commands.get(commandName);
    }

    private void createCommands() {
        this.commands.put("exit", new ExitCommand(this.controller));
        this.commands.put("login", new LoginCommand(this.controller));
        this.commands.put("logout", new LogoutCommand(this.controller));
        this.commands.put("player-create", new PlayerCreateCommand(this.controller));
        this.commands.put("player-delete", new PlayerDeleteCommand(this.controller));
        this.commands.put("statistics-show", new StatisticsShowCommand(this.controller));
        this.commands.put("team-add", new TeamAddCommand(this.controller));
        this.commands.put("team-create", new TeamCreateCommand(this.controller));
        this.commands.put("team-delete", new TeamDeleteCommand(this.controller));
        this.commands.put("team-remove", new TeamRemoveCommand(this.controller));
        this.commands.put("tournament-add", new TournamentAddCommand(this.controller));
        this.commands.put("tournament-create", new TournamentCreateCommand(this.controller));
        this.commands.put("tournament-delete", new TournamentDeleteCommand(this.controller));
        this.commands.put("tournament-list", new TournamentListCommand(this.controller));
        this.commands.put("tournament-matchmaking", new TournamentMatchmakingCommand(this.controller));
        this.commands.put("tournament-remove", new TournamentRemoveCommand(this.controller));
    }

    private void ensureNull() {
        this.commands.put(null, null);
    }
}
