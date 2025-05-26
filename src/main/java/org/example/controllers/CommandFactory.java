package org.example.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.example.utils.Console;
import org.example.utils.Error;
import org.example.utils.Role;
import org.example.views.Command;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CommandFactory {
    private final Controller controller;
    private final HashMap<String, Command> commands;

    public CommandFactory() {
        this.controller = new Controller();
        this.commands = new HashMap<>();
        this.createCommands();
        this.ensureNull();
    }

    public Optional<Command> getOptionalCommand(String commandName) {
        return Optional.ofNullable(this.commands.get(commandName));
    }

    private void ensureNull() {
        this.commands.put(null, null);
    }

    private void createCommands() {
        this.commands.put("exit", new Command(this.controller, 0, Error.NONE) {
            @Override
            protected Error executionTemplate(String[] args) {
                CommandFactory.this.commands.put("exit", null);
                return Error.NONE;
            }
        });
        this.commands.put("login", new Command(this.controller, 2, Error.ALREADY_LOGGED_ON, Role.GUEST) {
            @Override
            protected Error executionTemplate(String[] args) {
                return this.getController().login(args[0], args[1]);
            }
        });
        this.commands.put("logout", new Command(this.controller, 0, Error.NOT_LOGGED_ON, Role.ADMIN, Role.PLAYER) {
            @Override
            protected Error executionTemplate(String[] args) {
                this.getController().logout();
                return Error.NONE;
            }
        });
        this.commands.put("player-delete", new Command(this.controller, 1, Error.NO_PERMISSION, Role.ADMIN) {
            @Override
            protected Error executionTemplate(String[] args) {
                Error error = Error.INCORRECT_ARGUMENT_FORMAT;
                if (args[0].matches("^\\d{8}[A-Za-z]$")) {
                    error = !this.getController().isPlayerParticipatingInAInProgressTournament(args[0]) ?
                            this.getController().deletePlayer(args[0]) :
                            Error.PARTICIPANT_ON_TOURNAMENT_IN_PROGRESS;
                }
                return error;
            }
        });
        this.commands.put("player-create", new Command(this.controller, 3, Error.NO_PERMISSION, Role.ADMIN) {
            @Override
            protected Error executionTemplate(String[] args) {
                return args[2].matches("^\\d{8}[A-Za-z]$") ?
                        this.getController().createPlayer(args[0], args[1], args[2]) :
                        Error.INCORRECT_ARGUMENT_FORMAT;
            }
        });
        this.commands.put("statistics-show", new Command(this.controller, 0, Error.NO_PERMISSION, Role.PLAYER) {
            @Override
            protected Error executionTemplate(String[] args) {
                Console.getInstance().println(this.getController().showStats(args.length >= 1 ? args[0] : null));
                return Error.NONE;
            }
        });
        this.commands.put("team-add", new Command(this.controller, 2, Error.NO_PERMISSION, Role.ADMIN) {
            @Override
            protected Error executionTemplate(String[] args) {
                return args[0].matches("^\\d{8}[A-Za-z]$") && args[1].matches("[A-Za-z]+") ?
                        this.getController().addToTeam(args[0], args[1]) :
                        Error.INCORRECT_ARGUMENT_FORMAT;
            }
        });
        this.commands.put("team-create", new Command(this.controller, 1, Error.NO_PERMISSION, Role.ADMIN) {
            @Override
            protected Error executionTemplate(String[] args) {
                return this.getController().createTeam(args[0]);
            }
        });
        this.commands.put("team-delete", new Command(this.controller, 1, Error.NO_PERMISSION, Role.ADMIN) {
            @Override
            protected Error executionTemplate(String[] args) {
                return this.getController().deleteTeam(args[0]);
            }
        });
        this.commands.put("team-remove", new Command(this.controller, 0, Error.NO_PERMISSION, Role.PLAYER) {
            @Override
            protected Error executionTemplate(String[] args) {
                return this.getController().removeFromTeam();
            }
        });
        this.commands.put("tournament-add", new Command(this.controller, 1, Error.NO_PERMISSION, Role.PLAYER) {
            @Override
            protected Error executionTemplate(String[] args) {
                return this.getController().addToTournament(args[0], args.length > 1 ? args[1] : null);
            }
        });
        this.commands.put("tournament-create", new Command(this.controller, 5, Error.NO_PERMISSION, Role.ADMIN) {
            @Override
            protected Error executionTemplate(String[] args) {
                String dateFormat = "\\d{2}-\\d{2}-\\d{4}";
                return args[1].matches(dateFormat) && args[2].matches(dateFormat) ?
                        this.getController().createTournament(args[0], args[1], args[2], args[3], args[4]) :
                        Error.INCORRECT_ARGUMENT_FORMAT;
            }
        });
        this.commands.put("tournament-delete", new Command(this.controller, 1, Error.NO_PERMISSION, Role.ADMIN) {
            @Override
            protected Error executionTemplate(String[] args) {
                return this.getController().deleteTournament(args[0]);
            }
        });
        this.commands.put("tournament-list", new Command(this.controller, 0, Error.NONE) {
            @Override
            protected Error executionTemplate(String[] args) {
                Console.getInstance().println(this.getController().listTournaments());
                return Error.NONE;
            }
        });
        this.commands.put("tournament-matchmaking", new Command(this.controller, 1, Error.NO_PERMISSION, Role.ADMIN) {
            @Override
            protected Error executionTemplate(String[] args) {
                return this.getController().tournamentMatchmake(args[0], args.length > 1 ? args[1] : null, Arrays.stream(args).skip(2).toArray(String[]::new));
            }
        });
        this.commands.put("tournament-remove", new Command(this.controller, 1, Error.NO_PERMISSION, Role.PLAYER) {
            @Override
            protected Error executionTemplate(String[] args) {
                return this.getController().removeFromTournament(args[0], args.length > 1 ? args[1] : null);
            }
        });
        this.commands.put("load-data", new Command(this.controller, 0, Error.NONE) {
            private final String path = new File("").getAbsolutePath() + "/src/main/resources/dbfiles/";

            @Override
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
                    array.forEach(player -> {
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
                    array.forEach(team -> {
                        JsonObject object = team.getAsJsonObject();
                        String admin = object.get("admin") != null ? object.get("admin").getAsString() : null;
                        String name = object.get("name") != null ? object.get("name").getAsString() : null;
                        JsonArray jsonPlayers = object.get("players").getAsJsonArray();
                        List<String> playerDNIs = new ArrayList<>();
                        jsonPlayers.forEach(player -> playerDNIs.add(player.getAsString()));
                        this.getController().createTeam(admin, name, playerDNIs.toArray(new String[0]));
                    });
                } catch (IOException ex) {
                    assert false : ex.getMessage();
                }
            }

            private void readTournaments() {
                try (JsonReader reader = new JsonReader(new FileReader(path + "tournaments.json"))) {
                    JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();
                    array.forEach(tournament -> {
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
                    array.forEach(user -> {
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
        });
        this.commands.put("save-data", new Command(this.controller, 0, Error.NONE) {
            private final String path = new File("").getAbsolutePath() + "/src/main/resources/dbfiles/";

            @Override
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
        });
    }
}
