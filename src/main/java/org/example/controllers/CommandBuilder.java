package org.example.controllers;

import org.example.models.Role;
import org.example.views.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandBuilder {
    private final Map<Role, Map<String, Command>> commandMap;

    public CommandBuilder() {
        this.commandMap = new HashMap<>();
    }

    public Map<Role, Map<String, Command>> buildCommands() {
        this.addCommands();
        return this.commandMap;
    }

    private void addCommands() {
        this.addCommand(new ExitCommand("exit", "", "Saves changes and exits the application."));
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

    private void addCommand(Role role, Command command) {
        this.commandMap.putIfAbsent(command.getName(), command);
    }
}
