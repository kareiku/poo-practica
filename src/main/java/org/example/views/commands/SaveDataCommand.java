package org.example.views.commands;

import com.google.gson.stream.JsonWriter;
import org.example.controllers.Controller;
import org.example.utils.Error;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveDataCommand extends Command {
    private final String path;

    public SaveDataCommand(Controller controller) {
        super(controller, 0, Error.NONE);
        this.path = new File("").getAbsolutePath() + "/src/main/resources/dbfiles/";
    }

    public Error executionTemplate(String[] args) {
        this.storePlayers();
        this.storeTeams();
        this.storeTournaments();
        return Error.NONE;
    }

    private void storePlayers() {
        try (JsonWriter ignored = new JsonWriter(new FileWriter(path + "players.json"))) {
            assert true;
        } catch (IOException ex) {
            assert false : ex.getMessage();
        }
        try (JsonWriter writer = new JsonWriter(new FileWriter(path + "players.json"))) {
            String[][] players = this.getController().getPlayers();
            writer.beginArray();
            for (String[] player : players) {
                writer.beginObject();
                writer.name("email").value(player[0]);
                writer.name("forename").value(player[1]);
                writer.name("surname").value(player[2]);
                writer.name("DNI").value(player[3]);
                writer.name("stats");
                writer.beginArray();
                String[] stats = player[4].split(",");
                for (String stat : stats) {
                    writer.value(stat);
                }
                writer.endArray();
                writer.endObject();
            }
            writer.endArray();
        } catch (IOException ex) {
            assert false : ex.getMessage();
        }
    }

    private void storeTeams() {
        try (JsonWriter ignored = new JsonWriter(new FileWriter(path + "teams.json"))) {
            assert true;
        } catch (IOException ex) {
            assert false : ex.getMessage();
        }
        try (JsonWriter writer = new JsonWriter(new FileWriter(path + "teams.json"))) {
            String[][] teams = this.getController().getTeams();
            writer.beginArray();
            for (String[] team : teams) {
                writer.beginObject();
                writer.name("admin").value(team[0]);
                writer.name("name").value(team[1]);
                writer.name("players");
                writer.beginArray();
                String[] players = team[2].split(",");
                for (String player : players) {
                    writer.value(player);
                }
                writer.endArray();
                writer.endObject();
            }
            writer.endArray();
        } catch (IOException ex) {
            assert false : ex.getMessage();
        }
    }

    private void storeTournaments() {
        try (JsonWriter ignored = new JsonWriter(new FileWriter(path + "tournaments.json"))) {
            assert true;
        } catch (IOException ex) {
            assert false : ex.getMessage();
        }
        try (JsonWriter writer = new JsonWriter(new FileWriter(path + "tournaments.json"))) {
            String[][] tournaments = this.getController().getTournaments();
            writer.beginArray();
            for (String[] tournament : tournaments) {
                writer.beginObject();
                writer.name("name").value(tournament[0]);
                writer.name("startDate").value(tournament[1]);
                writer.name("endDate").value(tournament[2]);
                writer.name("league").value(tournament[3]);
                writer.name("sport").value(tournament[4]);
                writer.endObject();
            }
            writer.endArray();
        } catch (IOException ex) {
            assert false : ex.getMessage();
        }
    }
}
