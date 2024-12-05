package org.example.controllers;

import org.example.utils.Error;
import org.example.models.*;

import java.util.Map;

public class Controller {
    private final Database database;
    private User currentUser;
    private final Map<String, User> users;
    private final Map<String, Player> players;
    private final Map<String, Team> teams;
    private final Map<String, Tournament> tournaments;

    public Controller() {
        this.database = new Database();
        this.currentUser = new User(Role.GUEST);
        this.players = this.database.loadPlayers();
        this.teams = this.database.loadTeams();
        this.tournaments = this.database.loadTournaments();
        this.users = this.database.loadUsers();
    }

    public boolean hasPermission(Role... roles) {
        boolean hasPermission = roles.length == 0;
        int i = 0;
        while (!hasPermission && i < roles.length) {
            if (roles[i] == this.currentUser.getRole()) {
                hasPermission = true;
            }
            i++;
        }
        return hasPermission;
    }

    public Error login(String email, String password) {
        Error error;
        User user = this.users.get(email);
        if (user == null) {
            error = Error.NO_SUCH_USER;
        } else {
            error = user.isPasswordValid(password) ? Error.NONE : Error.INCORRECT_PASSWORD;
            if (error == Error.NONE) {
                this.currentUser = user;
            }
        }
        return error;
    }

    public Error logout() {
        this.currentUser = new User(Role.GUEST);
        return Error.NONE;
    }

    public Error createPlayer(String forename, String surname, String DNI) {
        return this.players.putIfAbsent(DNI, new Player(forename, surname, DNI)) == null ? Error.NONE : Error.EXISTENT_PLAYER;
    }

    public Error deletePlayer(String DNI) {
        return this.players.remove(DNI) != null ? Error.NONE : Error.INEXISTENT_PLAYER;
    }

    public String showStats(String option) {
        Player[] objective = new Player[]{null};
        this.players.forEach((DNI, player) -> {
            if (player.isUser(this.currentUser)) {
                objective[0] = player;
            }
        });
        return objective[0].getStatsFormat(option);
    }

    public Error addToTeam(String DNI, String teamName) {
        // TODO
        return null;
    }

    public Error createTeam() {
        // TODO
        return null;
    }

    public Error deleteTeam() {
        // TODO
        return null;
    }

    public Error removeFromTeam() {
        // TODO
        return null;
    }

    public Error addToTournament(String identifier, String tournamentName) {
        // TODO
        return null;
    }

    public Error createTournament() {
        // TODO
        return null;
    }

    public Error deleteTournament() {
        // TODO
        return null;
    }

    public String listTournaments() {
        // TODO
        return "";
    }

    public Error tournamentMatchmake() {
        // TODO
        return null;
    }

    public Error removeTournament(String tournamentName) {
        // TODO
        return null;
    }
}
