package org.example.controllers;

import org.example.models.*;

import java.util.HashMap;
import java.util.Map;


public class Database {
    public Map<String, Player> loadPlayers() {
        Map<String, Player> players = new HashMap<>();
        for (Player player : new Player[]{
                new Player("eri@example.org", "Eri", "Love", "82628856E", 30.9, 51.7, 9.0, 70.2, 75.3),
                new Player("manzana@frutas.shop", "Fuji", "Manzana", "41936480M", 78.8, 28.3, 20.4, 70.6, 16.1),
                new Player(null, "Charmander", null, "61877850F", 44.0, 4.8, 48.1, 25.2, 71.6),
                new Player(null, "Squirtle", null, "17319361Q", 22.6, 88.4, 37.7, 70.0, 24.1),
                new Player(null, "Bulbasaur", null, "18039101V", 13.2, 15.2, 84.7, 81.5, 16.0),
                new Player("lorem@ipsum.sit", "Lorem", "Ipsum", "84208585L", 93.3, 43.9, 17.0, 86.8, 29.5),
                new Player(null, "Ruby", "Rose", "28209915R", 38.7, 22.6, 44.8, 62.6, 51.7),
                new Player(null, "Weiss", "Schnee", "12393644W", 44.8, 48.0, 15.5, 72.6, 10.1),
                new Player(null, "Blake", "Belladonna", "06044066B", 35.9, 40.0, 26.4, 41.4, 99.6),
                new Player(null, "Yang", "Xiao Long", "67112879Y", 52.7, 3.2, 30.4, 10.4, 65.9),
                new Player(null, "Ana", "Rincón", "50070951C", 68.9, 4.5, 28.4, 37.0, 60.7),
                new Player(null, "Kirby", "Poyo", "77481570K", 67.5, 84.7, 54.1, 70.1, 60.5)

        }) {
            players.putIfAbsent(player.getIdentifier(), player);
        }
        return players;
    }

    public Map<String, Team> loadTeams() {
        Map<String, Team> teams = new HashMap<>();
        for (Team team : new Team[]{
                new Team("Kanto starters", "61877850F|18039101V|17319361Q"),
                new Team("RWBY", "28209915R|12393644W|06044066B|67112879Y")
        }) {
            teams.putIfAbsent(team.getIdentifier(), team);
        }
        return teams;
    }

    public Map<String, Tournament> loadTournaments() {
        Map<String, Tournament> tournaments = new HashMap<>();
        for (Tournament tournament : new Tournament[]{
                new Tournament("Nougat", "02-12-2024", "05-12-2024", "Flower Cup", "Karting"),
                new Tournament("Fungi", "05-12-2024", "08-12-2024", "Mushroom Cup", "Karting")
        }) {
            tournaments.putIfAbsent(tournament.getName(), tournament);
        }
        return tournaments;
    }

    public Map<String, User> loadUsers() {
        Map<String, User> users = new HashMap<>();
        for (User user : new User[]{
                new User("root", "root", Role.ADMIN),
        new User("eri@example.org", "eriri", Role.PLAYER),
        new User("manzana@frutas.shop", "manzanita123", Role.PLAYER),
        new User("lorem@ipsum.sit", "lipsum", Role.PLAYER),
        new User("aaron.burr@hamilton.us", "sir", Role.ADMIN)

        }) {
            users.putIfAbsent(user.getEmail(), user);
        }
        return users;
    }
}
