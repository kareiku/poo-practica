package org.example.views.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.example.controllers.Controller;
import org.example.utils.Error;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadDataCommand extends Command {
    private final String path;

    public LoadDataCommand(Controller controller) {
        super(controller, 0, Error.NONE);
        this.path = new File("").getAbsolutePath() + "/src/main/resources/dbfiles/";
    }

    public Error executionTemplate(String[] args) {
        this.readPlayers();
        this.readTeams();
        this.readTournaments();
        this.readUsers();
        return Error.NONE;
    }

    private void readPlayers() {
        try (JsonReader reader = new JsonReader(new FileReader(path + "players.json"))) {
            JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();
            array.forEach((player) -> {
                JsonObject object = player.getAsJsonObject();
                String email = object.get("email") != null ? object.get("email").getAsString() : null;
                String forename = object.get("forename") != null ? object.get("forename").getAsString() : null;
                String surname = object.get("surname") != null ? object.get("surname").getAsString() : null;
                String DNI = object.get("DNI") != null ? object.get("DNI").getAsString() : null;
                JsonArray jsonStats = object.get("stats").getAsJsonArray();
                Double[] stats = new Double[jsonStats.size()];
                for (int i = 0; i < stats.length; i++) {
                    try {
                        stats[i] = Double.parseDouble(jsonStats.get(i).getAsString());
                    } catch (NumberFormatException ex) {
                        stats[i] = 0.0;
                    }
                }
                this.getController().createPlayer(email, forename, surname, DNI, stats);
            });
        } catch (Exception ex) {
            assert false : ex.getMessage();
        }
    }

    private void readTeams() {
        try (JsonReader reader = new JsonReader(new FileReader(path + "teams.json"))) {
            JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();
            array.forEach((team) -> {
                JsonObject object = team.getAsJsonObject();
                String admin = object.get("admin") != null ? object.get("admin").getAsString() : null;
                String name = object.get("name") != null ? object.get("name").getAsString() : null;
                JsonArray jsonPlayers = object.get("players").getAsJsonArray();
                List<String> playerDNIs = new ArrayList<>();
                jsonPlayers.forEach((player) -> playerDNIs.add(player.getAsString()));
                this.getController().createTeam(admin, name, playerDNIs.toArray(new String[0]));
            });
        } catch (IOException ex) {
            assert false : ex.getMessage();
        }
    }

    private void readTournaments() {
        try (JsonReader reader = new JsonReader(new FileReader(path + "tournaments.json"))) {
            JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();
            array.forEach((tournament) -> {
                JsonObject object = tournament.getAsJsonObject();
                String name = object.get("name") != null ? object.get("name").getAsString() : null;
                String startDate = object.get("startDate") != null ? object.get("startDate").getAsString() : null;
                String endDate = object.get("endDate") != null ? object.get("endDate").getAsString() : null;
                String league = object.get("league") != null ? object.get("league").getAsString() : null;
                String sport = object.get("sport") != null ? object.get("sport").getAsString() : null;
                this.getController().createTournament(name, startDate, endDate, league, sport);
            });
        } catch (IOException ex) {
            assert false : ex.getMessage();
        }
    }

    private void readUsers() {
        try (JsonReader reader = new JsonReader(new FileReader(path + "users.json"))) {
            JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();
            array.forEach((user) -> {
                JsonObject object = user.getAsJsonObject();
                String email = object.get("email") != null ? object.get("email").getAsString() : null;
                String password = object.get("password") != null ? object.get("password").getAsString() : null;
                String role = object.get("role") != null ? object.get("role").getAsString() : null;
                this.getController().loadUser(email, password, role);
            });
        } catch (IOException ex) {
            assert false : ex.getMessage();
        }
    }
}
