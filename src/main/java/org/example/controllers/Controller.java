package org.example.controllers;

import org.example.models.*;
import org.example.Error;
import org.example.views.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    private final User currentUser;
    private final Map<String, User> users;
    private final Map<String, Player> players;
    private final Map<String, Team> teams;
    private final Map<String, Tournament> tournaments;

    public Controller() {
        this.currentUser = new User(Role.GUEST);
        this.players = new HashMap<>();
        this.teams = new HashMap<>();
        this.tournaments = new HashMap<>();
        this.users = new HashMap<>();
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
