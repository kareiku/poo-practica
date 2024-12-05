package org.example.controllers;

import org.example.utils.Error;
import org.example.models.*;

import java.util.Date;
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
        Player player = this.players.get(DNI);
        return player != null && this.teams.get(teamName).add(player) ? Error.NONE : Error.INEXISTENT_PLAYER;
    }

    public Error createTeam(String name) {
        if (!this.teams.containsKey(name)) {
            this.teams.put(name, new Team(name));
            return Error.NONE;
        }
        return Error.EXISTENT_TEAM;
    }

    public Error deleteTeam(String name) {
        return this.teams.remove(name) != null ? Error.NONE : Error.INEXISTENT_TEAM;
    }

    public Error removeFromTeam(String DNI, String name) {
        Team team = this.teams.get(name);
        if (team != null) {
            Player player = this.players.get(DNI);
            if (player != null) {
                return team.remove(player) ? Error.NONE : Error.PLAYER_ALREADY_IN_TEAM;
            } else {
                return Error.INEXISTENT_PLAYER;
            }
        } else {
            return Error.INEXISTENT_TEAM;
        }
    }

    public Error addToTournament(String identifier, String tournamentName) {
        Participant participant = null;
        if (identifier != null) {
            if (identifier.matches("\\d{8}[A-Za-z]")) {
                participant = this.players.get(identifier);
            } else {
                participant = this.teams.get(identifier);
            }
        } else {
            return Error.INCORRECT_ARGUMENT_FORMAT;
        }
        if (participant != null) {
            Tournament tournament = this.tournaments.get(tournamentName);
            if (tournament != null) {
                if (!tournament.inProgress() && !tournament.contains(participant)) {
                    return tournament.add(participant) ? Error.NONE : Error.PARTICIPANT_ALREADY_IN_TOURNAMENT;
                } else {
                    return Error.PARTICIPANT_ON_TOURNAMENT_IN_PROGRESS;
                }
            }
        }
    }

    public Error createTournament(String name, Date start, Date end, String league, String sport) {
        if (!this.tournaments.containsKey(name)) {
            this.tournaments.put(name, new Tournament(name, start, end, league, sport));
            return Error.NONE;
        }
        return Error.EXISTENT_TOURNAMENT;
    }

    public Error deleteTournament(String name) {
        return this.tournaments.remove(name) != null ? Error.NONE : Error.INEXISTENT_TOURNAMENT;
    }

    public String listTournaments() {
        switch (this.currentUser.getRole()) {
            case ADMIN:
                this.tournaments.forEach((name, tournament) -> {
                    if (tournament.hasFinished()) {
                        tournaments.remove(name);
                    }
                });
                return this.tournamentListing(false);
            case PLAYER:
                return this.tournamentListing(false);
            case GUEST:
                return this.tournamentListing(true);
        }
        return null;
    }

    private String tournamentListing(boolean random) {
        StringBuilder format = new StringBuilder();
        this.tournaments.forEach((name, tournament) -> format.append(tournament.getFormat()));
        return format.toString();
    }

    public Error tournamentMatchmake(String option) {
        if ("-m".equals(option)) {
            // TODO
            return null;
        } else if ("-a".equals(option)) {
            // TODO
            return null;
        } else {
            return Error.NO_SUCH_OPTION;
        }
    }

    public Error removeTournament(String tournamentName, String option) {
        if ("-e".equals(option)) {
            // TODO
            return null;
        } else {
            // TODO
            return null;
        }
    }
}
