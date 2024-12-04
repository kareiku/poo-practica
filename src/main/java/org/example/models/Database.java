package org.example.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Database {
    private static final String path = "../../../../resources/schemas/sportsmanager/";
    private final String table;

    public Database(String table) {
        this.table = table;
    }

    private Deque<String> loadData() {
        Deque<String> data = new ArrayDeque<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path + this.table));
            String line;
            while ((line = reader.readLine()) != null) {
                data.push(line);
            }
        } catch (IOException ex) {
            assert false;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    assert false;
                }
            }
        }
        return data;
    }

    private void storeData(String line) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path + this.table, true))) {
            writer.println(line);
        } catch (IOException ex) {
            assert false;
        }
    }

    private void emptyTable() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path + this.table))) {
            // fixme jump a line
            //  and clear the rest
        } catch (IOException ex) {
            assert false;
        }
    }

    public Set<User> loadUsers() {
        Set<User> users = new HashSet<>();
        Deque<String> usersData = new Database("users.csv").loadData();
        usersData.pop();
        while (!usersData.isEmpty()) {
            users.add(this.parseUser(usersData.pop()));
        }
        return users;
    }

    private User parseUser(String userData) {
        String[] userParts = userData.split(",");
        return new User(userParts[0], userParts[1], parseRole(userParts[2]));
    }

    private Role parseRole(String roleName) {
        roleName = roleName.toLowerCase();
        if (roleName.equals("admin")) {
            return Role.ADMIN;
        } else if (roleName.equals("player")) {
            return Role.PLAYER;
        } else {
            return Role.GUEST;
        }
    }

    public void storeUsers(Set<User> users) {
        for (User user : users) {
            this.storeData(this.unparseUser(user));
        }
    }

    private String unparseUser(User user) {
        return user.getEmail() + "," + user.getPassword() + "," + this.unparseRole(user.getRole());
    }

    private String unparseRole(Role role) {
        if (role == Role.ADMIN) {
            return "Admin";
        } else if (role == Role.PLAYER) {
            return "Player";
        } else {
            return "Guest";
        }
    }

    public Set<Player> loadPlayers() {
        Set<Player> players = new HashSet<>();
        Deque<String> playersData = new Database("players.csv").loadData();
        playersData.pop();
        while (!playersData.isEmpty()) {
            players.add(this.parsePlayer(playersData.pop());
        }
        return players;
    }

    private Player parsePlayer(String playerData) {
        String[] playerParts = playerData.split(",");
        for (int i = 0; i < playerParts.length; i++) {
            if (playerParts[i].isEmpty()) {
                playerParts[i] = null;
            }
        }
        Player player = new Player(playerParts[1], playerParts[2], playerParts[3]);
    }

    public void storePlayers(Set<Player> players) {
    }

    private String unparsePlayer(Player player) {
    }

    public Set<Team> loadTeams() {
        Set<Team> teams = new HashSet<>();
        Deque<String> teamsData = new Database("teams.csv").loadData();
        teamsData.pop();
        while (!teamsData.isEmpty()) {
            teams.add(this.parseTeam(teamsData.pop()));
        }
    }

    private Team parseTeam(String teamData) {
    }

    public void storeTeams(Set<Team> teams) {
    }

    public List<Tournament> loadTournaments() {
        List<Tournament> tournaments = new LinkedList<>();
        Deque<String> tournamentsData = new Database("tournaments.csv").loadData();
        tournamentsData.pop();
        while (!tournamentsData.isEmpty()) {
            tournaments.add(this.parseTournament(tournamentsData.pop()));
        }
        return tournaments;
    }

    private Tournament parseTournament(String tournamentData) {
    }

    public void storeTournaments(Set<Tournament> tournaments) {
    }
}
