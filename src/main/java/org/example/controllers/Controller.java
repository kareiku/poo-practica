package org.example.controllers;

import org.example.models.*;
import org.example.utils.Error;
import org.example.utils.Role;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        return this.players.entrySet().stream().filter(entry -> entry.getValue().isUser(this.currentUser)).findFirst().map(Map.Entry::getValue).orElse(null);
    }

    private Team getTeamFromPlayer(Player player) {
        return this.teams.entrySet().stream().filter(entry -> entry.getValue().contains(player)).findFirst().map(Map.Entry::getValue).orElse(null);
    }

    public boolean hasPermission(Role... roles) {
        return roles == null || roles.length == 0 || Arrays.stream(roles).anyMatch(role -> role == this.currentUser.role());
    }

    public Error login(String email, String password) {
        Error error = Error.NONE;
        User user = this.users.get(email);
        if (user == null) {
            error = Error.NO_SUCH_USER;
        } else if (user.isPasswordCorrect(password)) {
            this.currentUser = user;
        } else {
            error = Error.INCORRECT_PASSWORD;
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
        return this.players.putIfAbsent(DNI, new Player(email, forename, surname, DNI, stats)) == null ? Error.NONE : Error.EXISTENT_PLAYER;
    }

    public Error deletePlayer(String DNI) {
        return this.players.remove(DNI) != null ? Error.NONE : Error.INEXISTENT_PLAYER;
    }

    public boolean isPlayerParticipatingInAInProgressTournament(String DNI) {
        return this.tournaments.entrySet().stream().anyMatch(entry -> entry.getValue().contains(this.players.get(DNI)));
    }

    public String showStats(String option) {
        if ("-e".equals(option)) {
            return this.getTeamFromPlayer(this.getPlayerFromCurrentUser()).getStatsFormat();
        } else {
            return this.getPlayerFromCurrentUser().getStatsFormat(option);
        }
    }

    public Error addToTeam(String DNI, String teamName) {
        Player player = this.players.get(DNI);
        Team team = this.teams.get(teamName);
        return player != null && team != null && team.add(player) ? Error.NONE : Error.INEXISTENT_PLAYER;
    }

    public Error createTeam(String name) {
        return this.createTeam(this.currentUser.email(), name);
    }

    public Error createTeam(String admin, String name, String... playerDNIs) {
        return this.teams.putIfAbsent(name, new Team(admin, name, this.players.values()
                .stream().filter(player -> Arrays.asList(playerDNIs).contains(player.DNI()))
                .toArray(Player[]::new))) == null ? Error.NONE : Error.EXISTENT_TEAM;
    }

    public Error deleteTeam(String name) {
        return this.teams.remove(name) != null ? Error.NONE : Error.INEXISTENT_TEAM;
    }

    public Error removeFromTeam() {
        Player player = this.getPlayerFromCurrentUser();
        if (player != null) {
            Team team = this.getTeamFromPlayer(player);
            if (team != null) {
                return team.remove(player) ? Error.NONE : Error.PLAYER_ALREADY_IN_TEAM;
            } else {
                return Error.INEXISTENT_TEAM;
            }
        } else {
            return Error.INEXISTENT_PLAYER;
        }
    }

    public Error addToTournament(String tournamentName, String option) {
        Tournament tournament = this.tournaments.get(tournamentName);
        if (tournament != null) {
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
        } else {
            return Error.INEXISTENT_TOURNAMENT;
        }
        return Error.NONE;
    }

    public Error createTournament(String name, String startDate, String endDate, String league, String sport) {
        return this.tournaments.putIfAbsent(name, new Tournament(name, startDate, endDate, league, sport)) == null ? Error.NONE : Error.EXISTENT_TOURNAMENT;
    }

    public Error deleteTournament(String name) {
        return this.tournaments.remove(name) != null ? Error.NONE : Error.INEXISTENT_TOURNAMENT;
    }

    public String listTournaments() {
        if (this.currentUser.role() == Role.ADMIN) {
            this.tournaments.entrySet().removeIf(entry -> entry.getValue().hasFinished());
        }
        return this.tournamentListing(this.currentUser.role());
    }

    private String tournamentListing(Role role) {
        StringBuilder format = new StringBuilder();
        List<Tournament> tournaments = new ArrayList<>(this.tournaments.values());
        switch (role) {
            case ADMIN:
            case PLAYER:
                tournaments.forEach((tournament -> format.append(tournament.getFormat()).append(tournament.getSortedParticipantsFormat())));
                break;
            case GUEST:
                Collections.shuffle(tournaments);
                tournaments.forEach((tournament -> format.append(tournament.getFormat()).append(System.lineSeparator())));
                break;
        }
        return format.toString();
    }

    public Error tournamentMatchmake(String tournamentName, String option, String... participantIdentifiers) {
        Tournament tournament = this.tournaments.get(tournamentName);
        if (tournament != null) {
            Participant[] participants = new Participant[participantIdentifiers.length];
            IntStream.range(0, participantIdentifiers.length).forEach(i ->
                    participants[i] = participantIdentifiers[i].matches("^\\d{8}[A-Za-z]$") ?
                            this.players.get(participantIdentifiers[i]) :
                            this.teams.get(participantIdentifiers[i])
            );
            switch (option) {
                case "-m":
                    tournament.manualMatchmake(participants);
                    return Error.NONE;
                case "-a":
                    tournament.randomMatchmake(participants);
                    return Error.NONE;
                default:
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

    private Role parseRole(String roleName) {
        return Stream.of(new AbstractMap.SimpleEntry<>("admin", Role.ADMIN), new AbstractMap.SimpleEntry<>("player", Role.PLAYER), new AbstractMap.SimpleEntry<>("guest", Role.GUEST))
                .filter(role -> role.getKey().equalsIgnoreCase(roleName))
                .findFirst().map(Map.Entry::getValue).orElse(null);
    }

    public String[][] getPlayers() {
        String[][] values = new String[this.players.size()][5];
        Player[] players = this.players.values().toArray(new Player[0]);
        for (int i = 0; i < values.length; i++) {
            values[i][0] = players[i].email();
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
            teams[i].players().forEach(player -> players.append(player.DNI()).append(','));
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
