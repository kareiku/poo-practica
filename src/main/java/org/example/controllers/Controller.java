package org.example.controllers;

import org.example.Error;
import org.example.models.*;

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

    public boolean hasPermission(Role[] roles) {
        boolean hasPermission = false;
        int i = 0;
        while (!hasPermission && i < roles.length) {
            if (roles[i] == currentUser.getRole()) {
                hasPermission = true;
            }
            i++;
        }
        return hasPermission;
    }

    public Error login(String email, String password) {
        Error error = Error.NONE;
        // TODO
        return error;
    }

    public Error addPlayer(String forename, String surname, String DNI) {
        return this.players.putIfAbsent(DNI, new Player(forename, surname, DNI)) == null ? Error.NONE : Error.EXISTENT_PLAYER;
    }

    public Error deletePlayer(String DNI) {
        return this.players.remove(DNI) != null ? Error.NONE : Error.INEXISTENT_PLAYER;
    }
}
