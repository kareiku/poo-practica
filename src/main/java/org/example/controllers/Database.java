package org.example.controllers;

import org.example.models.Player;
import org.example.models.Team;
import org.example.models.Tournament;
import org.example.models.User;

import java.io.*;
import java.util.*;

public class Database {
    private static final String PATH = "../../../../resources/schemas/sportsmanager/";

    private <T> List<T> loadData(String filename, CSVParser<T> parser) {
        List<T> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(PATH + filename))) {
            String[] row;
            while((row = reader.readNext())!= null){
                data.add(parser.parse(row, false));
            }
        } catch (Exception ex) {
            assert false;
        }
        return data;
    }

    private void storeData(String file, Deque<String> data) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(PATH + file))) {
            while (!data.isEmpty()) {

                writer.writeNext();
            }
        } catch (Exception ex) {
            assert false;
        }
    }

    private void emptyFile(String file) {
        try (FileWriter writer = new FileWriter(PATH + file)) {
            assert true;
        } catch (Exception ex) {
            assert false;
        }
    }

    public Map<String, Player> loadPlayers() {
        Map<String, Player> players = new HashMap<>();
        List<Player> data = this.loadData("players.csv");
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
}
