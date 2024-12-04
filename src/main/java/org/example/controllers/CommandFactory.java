package org.example.controllers;

import org.example.views.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Command> commands;

    public CommandFactory() {
        this.commands = new HashMap<>();
    }

    public Command getCommand(String commandName) {
        return this.commands.get(commandName);
    }

    private void addCommand(Command command) {
        this.commands.putIfAbsent(command.getName(), command);
    }

    private void createCommands() {
        this.addCommand(new LoginCommand("login", "<email>;<password>", "Attempts to log in the specified user"));
        this.addCommand(new LogoutCommand("logout", "", "Attempts to log out the currently logged on user."));
        this.addCommand(new PlayerCreateCommand("player-create", "<DNI>;<name>;<surnames>", "Creates a player in the system."));
        this.addCommand(new PlayerDeleteCommand("player-delete", "<DNI>", "Removes a player from the system."));
        this.addCommand(new StatisticsShowCommand("statistics-show", "", "Shows the statistics of the logged player or their team, in their case."));
        this.addCommand(new TeamAddCommand("team-add", "<DNI>;<team name>", "Adds the specified player to the specified team."));
        this.addCommand(new TeamCreateCommand("team-create", "<name>", "Adds a team to the database."));
        this.addCommand(new TeamDeleteCommand("team-delete", "<name>", "Removes a team from the database."));
        this.addCommand(new TeamRemoveCommand("team-remove", "<DNI>", "Removes the specified player from the specified team."));
        this.addCommand(new TournamentAddCommand("tournament-add", "<DNI | team name>;<tournament name>", "Adds a player or a team to a tournament."));
        this.addCommand(new TournamentCreateCommand("tournament-create", "<name>;<start date>;<end date>;<league>;<sport>", "Creates a tournament."));
        this.addCommand(new TournamentDeleteCommand("tournament-delete", "<name>", "Deletes the specified tournament."));
        this.addCommand(new TournamentListCommand("tournament-list", "", "List the ongoing tournaments. Reduced format to the ones you're participant, if such user is logged."));
        this.addCommand(new TournamentMatchmakingCommand("tournament-matchmaking", "", ""));
        this.addCommand(new TournamentRemoveCommand("tournament-remove", "<tournament name>", "Deletes a tournament, regardless of its current state."));
    }
}