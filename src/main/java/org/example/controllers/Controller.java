package org.example.controllers;

import org.example.models.*;
import org.example.utils.Error;
import org.example.utils.Role;

import java.util.*;

public class Controller {
    private User currentUser;
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
        boolean hasPermission = roles == null || roles.length == 0;
        int i = 0;
        while (!hasPermission && i < roles.length) {
            hasPermission = roles[i] == this.currentUser.role();
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
            error = user.isPasswordCorrect(password) ? Error.NONE : Error.INCORRECT_PASSWORD;
            if (error == Error.NONE) {
                this.currentUser = user;
            }
        }
        return error;
    }

    public void logout() {
        this.currentUser = new User(Role.GUEST);
    }

    public Error createPlayer(String forename, String surname, String DNI) {
        return this.createPlayer(null, forename, surname, DNI);
    }

    public Error createPlayer(String email, String forename, String surname, String DNI, Double... stats) {
        return this.players.putIfAbsent(DNI, new Player(email, forename, surname, DNI, stats)) != null ? Error.NONE : Error.EXISTENT_PLAYER;
    }

    public Error deletePlayer(String DNI) {
        return this.players.remove(DNI) != null ? Error.NONE : Error.INEXISTENT_PLAYER;
    }

    public boolean isPlayerParticipatingInAInProgressTournament(String DNI) {
        boolean[] isIt = {false};
        this.tournaments.forEach((name, tournament) -> isIt[0] |= tournament.contains(this.players.get(DNI)));
        return isIt[0];
    }

    public String showStats(String... options) {
        if ("-e".equals(options[0])) {
            return this.getTeamFromPlayer(this.getPlayerFromCurrentUser()).getStatsFormat();
        }
        return this.getPlayerFromCurrentUser().getStatsFormat(options[1]);
    }

    public Error addToTeam(String DNI, String teamName) {
        Player player = this.players.get(DNI);
        return player != null && this.teams.get(teamName).add(player) ? Error.NONE : Error.INEXISTENT_PLAYER;
    }

    public Error createTeam(String name) {
        return this.createTeam(this.currentUser.email(), name);
    }

    public Error createTeam(String admin, String name, String... playerDNIs) {
        if (!this.teams.containsKey(name)) {
            this.teams.put(name, new Team(admin, name, playerDNIs));
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
        if (!tournament.isInProgress()) {
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
        if (this.currentUser.role() == Role.ADMIN) {
            this.tournaments.forEach((name, tournament) -> {
                if (tournament.hasFinished()) {
                    tournaments.remove(name);
                }
            });
        }
        return this.tournamentListing(this.currentUser.role());
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

    public void loadUser(String email, String password, String role) {
        this.users.putIfAbsent(email, new User(email, password, this.parseRole(role)));
    }

    private Role parseRole(String inputRoleName) {
        Map<String, Role> roles = new HashMap<>();
        roles.put("admin", Role.ADMIN);
        roles.put("player", Role.PLAYER);
        roles.put("guest", Role.GUEST);
        Role[] objectiveRole = {null};
        roles.forEach((roleName, role) -> {
            if (roleName.equalsIgnoreCase(inputRoleName)) {
                objectiveRole[0] = role;
            }
        });
        return objectiveRole[0];
    }

    public String[][] getPlayers() {
        String[][] values = new String[this.players.size()][5];
        Player[] players = this.players.values().toArray(new Player[0]);
        for (int i = 0; i < values.length; i++) {
            values[i][0] = players[i].user().email();
            values[i][1] = players[i].forename();
            values[i][2] = players[i].surname();
            values[i][3] = players[i].DNI();
            StringBuilder stats = new StringBuilder();
            players[i].stats().forEach((category, stat) -> stats.append(stat).append(','));
            stats.deleteCharAt(stats.length() - 1);
            values[i][4] = stats.toString();
        }
        return values;
    }

    public String[][] getTeams() {
        String[][] values = new String[this.teams.size()][3];
        Team[] teams = this.teams.values().toArray(new Team[0]);
        for (int i = 0; i < values.length; i++) {
            values[i][0] = teams[i].admin();
            values[i][1] = teams[i].name();
            StringBuilder players = new StringBuilder();
            teams[i].players().forEach((player) -> players.append(player.DNI()).append(','));
            players.deleteCharAt(players.length() - 1);
            values[i][2] = players.toString();
        }
        return values;
    }

    public String[][] getTournaments() {
        String[][] values = new String[this.tournaments.size()][5];
        Tournament[] tournaments = this.tournaments.values().toArray(new Tournament[0]);
        for (int i = 0; i < values.length; i++) {
            values[i][0] = tournaments[i].name();
            values[i][1] = tournaments[i].startDate();
            values[i][2] = tournaments[i].endDate();
            values[i][3] = tournaments[i].league();
            values[i][4] = tournaments[i].sport();
        }
        return values;
    }
}
