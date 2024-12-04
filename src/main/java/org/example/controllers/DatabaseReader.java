package org.example.controllers;

import org.example.models.*;

import java.util.*;

public class DatabaseReader {
    public Set<User> loadUsers() {
        Set<User> users = new HashSet<>();
        Deque<String> usersData = new Database("users.csv").loadData();
        usersData.pop();
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

    public Set<Participant> loadParticipants() {
        Set<Participant> participants = new HashSet<>();
        Deque<String> playersData = new Database("players.csv").loadData();
        Deque<String> teamsData = new Database("teams.csv").loadData();
        playersData.pop();
        while (!playersData.isEmpty()) {
            String playerData = playersData.pop();
            // todo
        }
        teamsData.pop();
        while (!teamsData.isEmpty()) {
            String teamData = teamsData.pop();
            // todo
        }
        return participants;
    }

    public List<Tournament> loadTournaments() {
        List<Tournament> tournaments = new LinkedList<>();
        Deque<String> tournamentsData = new Database("tournaments.csv").loadData();
        tournamentsData.pop();
        while (!tournamentsData.isEmpty()) {
            String tournamentData = tournamentsData.pop();
            // todo
        }
        return tournaments;
    }
}
