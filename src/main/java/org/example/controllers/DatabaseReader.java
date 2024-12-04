package org.example.controllers;

import org.example.models.*;

import java.util.Deque;

public class DatabaseReader {
    public UserSet loadUsers() {
        UserSet users = new UserSet();
        Deque<String> usersData = new Database("users.csv").loadData();
        while (!usersData.isEmpty()) {
            String userData = usersData.pop();
            users.add(this.parseUser(userData));
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

    public ParticipantSet loadParticipants() {
        ParticipantSet participants = new ParticipantSet();
        Deque<String> playersData = new Database("players.csv").loadData();
        Deque<String> teamsData = new Database("teams.csv").loadData();
        while (!playersData.isEmpty()) {
            String playerData = playersData.pop();
            // todo
        }
        while (!teamsData.isEmpty()) {
            String teamData = playersData.pop();
            // todo
        }
        return participants;
    }

    public TournamentList loadTournaments() {
        TournamentList tournaments = new TournamentList();
        Deque<String> tournamentsData = new Database("tournaments.csv").loadData();
        while (!tournamentsData.isEmpty()) {
            String tournamentData tournamentsData.pop();
            // todo
        }
        return tournaments;
    }
}
