package org.example.controllers;

import org.example.models.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Database {
    public Map<String, Player> loadPlayers() {
        Map<String, Player> players = new HashMap<>();
        for (Player player : new Player[]{
                new Player("Kirby", "Poyo", "")
                // TODO
        }) {
            players.putIfAbsent(player.getIdentifier(), player);
        }
        return players;
    }

    public Map<String, Team> loadTeams() {
        Map<String, Team> teams = new HashMap<>();
        for (Team team : new Team[]{
                new Team("Kanto starters"),
                new Team("RWBY")
                // TODO
        }) {
            teams.putIfAbsent(team.getIdentifier(), team);
        }
        return teams;
    }

    public Map<String, Tournament> loadTournaments() {
        Map<String, Tournament> tournaments = new HashMap<>();
        for (Tournament tournament : new Tournament[]{
                new Tournament("Nougat", Date.valueOf(""), Date.valueOf(""), "Flower Cup", "Karting")
                // TODO
        }) {
            tournaments.putIfAbsent(tournament.getName(), tournament);
        }
        return tournaments;
    }

    public Map<String, User> loadUsers() {
        Map<String, User> users = new HashMap<>();
        for (User user : new User[]{
                new User("root", "root", Role.ADMIN),
                new User("eri@example.org", "eriri", Role.PLAYER)
                // TODO
        }) {
            users.putIfAbsent(user.getEmail(), user);
        }
        return users;
    }
}
