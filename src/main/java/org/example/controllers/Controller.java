package org.example.controllers;

import org.example.utils.Error;
import org.example.models.*;

import java.util.*;

public class Controller {
    private User currentUser;
    private final Map<String, User> users;
    private final Map<String, Player> players;
    private final Map<String, Team> teams;
    private final Map<String, Tournament> tournaments;

    public Controller() {
        Database database = new Database();
        this.currentUser = new User(Role.GUEST);
        this.players = database.loadPlayers();
        this.teams = database.loadTeams();
        this.tournaments = database.loadTournaments();
        this.users = database.loadUsers();
    }

    private Player getPlayerFromCurrentUser() {
        Player[] playerCurrentUser = {null};
        this.players.forEach((DNI, player) -> {
            if (player.isUser(this.currentUser)) {
                playerCurrentUser[0] = player;
            }
        });
        return playerCurrentUser[0];
    }

    private Team getTeamFromPlayer(Player player) {
        Team[] teamWithPlayer = {null};
        this.teams.forEach((name, team) -> {
            if (team.contains(player)) {
                teamWithPlayer[0] = team;
            }
        });
        return teamWithPlayer[0];
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

    public boolean isPlayerParticipatingInAInProgressTournament(String DNI) {
        boolean[] isIt = {false};
        this.tournaments.forEach((name, tournament) -> isIt[0] = tournament.contains(this.players.get(DNI)));
        return isIt[0];
    }

    public String showStats(String option) {
        if ("-e".equals(option)) {
            return this.getTeamFromPlayer(this.getPlayerFromCurrentUser()).getStatsFormat(null);
        }
        return this.getPlayerFromCurrentUser().getStatsFormat(option);
    }

    public Error addToTeam(String DNI, String teamName) {
        Player player = this.players.get(DNI);
        return player != null && this.teams.get(teamName).add(player) ? Error.NONE : Error.INEXISTENT_PLAYER;
    }

    public Error createTeam(String name) {
        if (!this.teams.containsKey(name)) {
            this.teams.put(name, new Team(this.currentUser.getEmail(), name));
            return Error.NONE;
        }
        return Error.EXISTENT_TEAM;
    }

    public Error deleteTeam(String name) {
        return this.teams.remove(name) != null ? Error.NONE : Error.INEXISTENT_TEAM;
    }

    public Error removeFromTeam(String DNI) {
        Team team = this.getTeamFromPlayer(this.players.get(DNI));
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

    public Error addToTournament(String tournamentName, String option) {
        Tournament tournament = this.tournaments.get(tournamentName);
        if (!tournament.inProgress()) {
            Player player = this.getPlayerFromCurrentUser();
            if (player != null) {
                if (!tournament.contains(player)) {
                    if ("-e".equals(option)) {
                        Team team = getTeamFromPlayer(player);
                        if (team != null) {
                            return tournament.add(team) ? Error.NONE : Error.PARTICIPANT_ALREADY_IN_TOURNAMENT;
                        } else {
                            return Error.INEXISTENT_TEAM;
                        }
                    } else {
                        tournament.add(player);
                    }
                } else {
                    return Error.PARTICIPANT_ALREADY_IN_TOURNAMENT;
                }
            } else {
                return Error.INEXISTENT_PLAYER;
            }
        } else {
            return Error.TOURNAMENT_IN_PROGRESS;
        }
        return Error.NONE;
    }

    public Error createTournament(String name, String startDate, String endDate, String league, String sport) {
        if (!this.tournaments.containsKey(name)) {
            this.tournaments.put(name, new Tournament(name, startDate, endDate, league, sport));
            return Error.NONE;
        }
        return Error.EXISTENT_TOURNAMENT;
    }

    public Error deleteTournament(String name) {
        return this.tournaments.remove(name) != null ? Error.NONE : Error.INEXISTENT_TOURNAMENT;
    }

    public String listTournaments() {
        if (this.currentUser.getRole() == Role.ADMIN) {
            this.tournaments.forEach((name, tournament) -> {
                if (tournament.hasFinished()) {
                    tournaments.remove(name);
                }
            });
        }
        return this.tournamentListing(this.currentUser.getRole());
    }

    private String tournamentListing(Role role) {
        List<Tournament> tournaments = new ArrayList<>(this.tournaments.values());
        StringBuilder format = new StringBuilder();
        if (role == Role.ADMIN || role == Role.PLAYER) {
            tournaments.forEach((tournament -> format.append(tournament.getFormat()).append(tournament.getSortedParticipantsFormat())));
        } else {
            Collections.shuffle(tournaments);
        }
        tournaments.forEach((tournament -> format.append(tournament.getFormat()).append("\n")));
        return format.toString();
    }

    public Error tournamentMatchmake(String tournamentName, String option, String... participantIdentifiers) {
        Tournament tournament = this.tournaments.get(tournamentName);
        if (tournament != null) {
            Participant[] participants = new Participant[participantIdentifiers.length];
            for (int i = 2; i < participantIdentifiers.length; i++) {
                participants[i] = participantIdentifiers[i].matches("^\\d{8}[A-Za-z]$") ?
                        this.players.get(participantIdentifiers[i]) :
                        this.teams.get(participantIdentifiers[i]);
            }
            if ("-m".equals(option)) {
                tournament.manualMatchmake(participants);
                return Error.NONE;
            } else if ("-a".equals(option)) {
                tournament.randomMatchmake(participants);
                return Error.NONE;
            } else {
                return Error.NO_SUCH_OPTION;
            }
        } else {
            return Error.INEXISTENT_TOURNAMENT;
        }
    }

    public Error removeFromTournament(String tournamentName, String option) {
        Tournament tournament = this.tournaments.get(tournamentName);
        Player player = this.getPlayerFromCurrentUser();
        if ("-e".equals(option)) {
            return tournament.remove(this.getTeamFromPlayer(player)) ? Error.NONE : Error.INEXISTENT_TEAM;
        } else {
            return tournament.remove(player) ? Error.NONE : Error.INEXISTENT_PLAYER;
        }
    }
}
