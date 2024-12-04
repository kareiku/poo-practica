package org.example.controllers;

import org.example.models.*;
import org.example.Error;
import org.example.views.commands.Command;
import org.example.views.commands.ExitCommand;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Controller {
    private final User currentUser;
    private final Set<User> users;
    private final Set<Player> players;
    private final Set<Team> teams;
    private final List<Tournament> tournaments;
    private final Map<String, Command> commands;

    public Controller() {
        this.currentUser = new User(Role.GUEST);
        this.players = new Database("players.csv").loadPlayers();
        this.teams = new Database("teams.csv").loadTeams();
        this.tournaments = new Database("tournaments.csv").loadTournaments();
        this.users = new Database("users.csv").loadUsers();
        this.commands = new CommandBuilder().buildCommands();
    }

    public void handleInput(String statement) {
        statement = statement.toLowerCase();
        if (!statement.isEmpty()) {
            String commandName = statement.split("\\s+", 2)[0];
            String[] args = statement.split("\\s+", 2)[1].split(";");
            Command command = commands.get(commandName);
            if (command != null &&) {
                if (new PermissionChecker().hasPermission(command, currentUser.getRole())) {
                    command.execute(args);
                }
            }
        }
        if (this.exitHasBeenExecuted()) {
            new Database("players.csv").storePlayers(this.players);
            new Database("teams.csv").storePlayers(this.teams);
            new Database("tournaments.csv").storePlayers(this.tournaments);
            new Database("users.csv").storePlayers(this.users);
        }
    }

    public boolean exitHasBeenExecuted() {
        return ((ExitCommand) commands.get("exit")).hasBeenExecuted();
    }

    private Player parsePlayer(String identifier) {
        Player res = null;
        Iterator<Player> iterator = this.players.iterator();
        while (res == null && iterator.hasNext()) {
            Player player = iterator.next();
            if (player.matches(identifier)) {
                res = player;
            }
        }
        return res;
    }

    @Deprecated
    private Error createPlayer(String... args) {
        if (!this.players.add(new Player(args[0], args[1], args[2]))) {
            return Error.EXISTENT_PLAYER;
        }
        return Error.NONE;
    }

    @Deprecated
    private void deletePlayer(String... args) {
        this.players.remove(new Player(null, null, args[0]));
    }
}
