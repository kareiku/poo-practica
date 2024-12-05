package org.example.controllers;

import org.example.models.Player;
import org.example.models.Team;
import org.example.models.Tournament;
import org.example.models.User;

import java.util.HashMap;
import java.util.Map;

public class Database {
    public Map<String, Player> loadPlayers() {
        Map<String, Player> players = new HashMap<>();
        // TODO
        return players;
    }

    private Player parsePlayer(String playerData) {
        Player player = null;
        // TODO
        return player;
    }

    public Map<String, Team> loadTeams() {
        Map<String, Team> teams = new HashMap<>();
        // TODO
        return teams;
    }

    private Team parseTeam(String teamData) {
        Team team = null;
        // TODO
        return team;
    }

    public Map<String, Tournament> loadTournaments() {
        Map<String, Tournament> tournaments = new HashMap<>();
        // TODO
        return tournaments;
    }

    private Tournament parseTournament(String tournamentData) {
        Tournament tournament = null;
        // TODO
        return tournament;
    }

    public Map<String, User> loadUsers() {
        Map<String, User> users = new HashMap<>();
        // TODO
        return users;
    }

    private User parseUser(String userData) {
        User user = null;
        // TODO
        return user;
    }
}
