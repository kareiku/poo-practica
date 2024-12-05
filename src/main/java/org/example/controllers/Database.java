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

    public Map<String, Team> loadTeams() {
        Map<String, Team> teams = new HashMap<>();
        // TODO
        return teams;
    }

    public Map<String, Tournament> loadTournaments() {
        Map<String, Tournament> tournaments = new HashMap<>();
        // TODO
        return tournaments;
    }

    public Map<String, User> loadUsers() {
        Map<String, User> users = new HashMap<>();
        // TODO
        return users;
    }

    public void storePlayers() {
        // TODO
    }

    public void storeTeams() {
        // TODO
    }

    public void storeTournaments() {
        // TODO
    }

    public void storeUsers() {
        // TODO
    }

    private Player parsePlayer(String playerData) {
        Player player = null;
        // TODO
        return player;
    }

    private Team parseTeam(String teamData) {
        Team team = null;
        // TODO
        return team;
    }

    private Tournament parseTournament(String tournamentData) {
        Tournament tournament = null;
        // TODO
        return tournament;
    }

    private User parseUser(String userData) {
        User user = null;
        // TODO
        return user;
    }

    private String unParsePlayer(Player player) {
        String playerData = null;
        // TODO
        return playerData;
    }

    private String unParseTeam(Team team) {
        String teamData = null;
        // TODO
        return teamData;
    }

    private String unParseTournament(Tournament tournament) {
        String tournamentData = null;
        // TODO
        return tournamentData;
    }

    private String unParseUser(User user) {
        String userData = null;
        // TODO
        return userData;
    }
}
