package org.example.controllers;

import org.example.utils.Console;
import org.example.utils.Error;
import org.example.utils.Role;
import org.example.views.commands.*;

import java.util.HashMap;

public class CommandFactory {
    private final Controller controller;
    private final HashMap<String, Command> commands;

    public CommandFactory() {
        this.controller = new Controller();
        this.commands = new HashMap<>();
        this.createCommands();
        this.ensureNull();
    }

    public Command getCommand(String commandName) {
        return this.commands.get(commandName);
    }

    private void ensureNull() {
        this.commands.put(null, null);
    }

    private void createCommands() {
        this.commands.put("exit", new ExitCommand(this.controller));
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
                    if (!this.getController().isPlayerParticipatingInAInProgressTournament(args[0])) {
                        error = this.getController().deletePlayer(args[0]);
                    } else {
                        error = Error.PARTICIPANT_ON_TOURNAMENT_IN_PROGRESS;
                    }
                }
                return error;
            }
        });
        this.commands.put("player-create", new Command(this.controller, 3, Error.NO_PERMISSION, Role.ADMIN) {
            @Override
            protected Error executionTemplate(String[] args) {
                Error error = Error.INCORRECT_ARGUMENT_FORMAT;
                if (args[2].matches("^\\d{8}[A-Za-z]$")) {
                    error = this.getController().createPlayer(args[0], args[1], args[2]);
                }
                return error;
            }
        });
        this.commands.put("statistics-show", new Command(this.controller, 0, Error.NO_PERMISSION, Role.PLAYER) {
            @Override
            protected Error executionTemplate(String[] args) {
                String teamOption = !args[0].isEmpty() ? args[0] : null;
                String formatOption = !args[1].isEmpty() ? args[1] : null;
                Console.getInstance().println(this.getController().showStats(teamOption, formatOption));
                return Error.NONE;
            }
        });
        this.commands.put("team-add", new Command(this.controller, 2, Error.NO_PERMISSION, Role.ADMIN) {
            @Override
            protected Error executionTemplate(String[] args) {
                if (args[0].matches("^\\d{8}[A-Za-z]$") && args[1].matches("[A-Za-z]+")) {
                    return this.getController().addToTeam(args[0], args[1]);
                } else {
                    return Error.INCORRECT_ARGUMENT_FORMAT;
                }
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
                return this.getController().removeFromTeam(args[0]);
            }
        });
        this.commands.put("tournament-add", new Command(this.controller, 1, Error.NO_PERMISSION, Role.ADMIN) {
            @Override

            protected Error executionTemplate(String[] args) {
                return this.getController().addToTournament(args[0], args.length > 1 ? args[1] : null);
            }
        });
        this.commands.put("tournament-create", new Command(this.controller, 5, Error.NO_PERMISSION, Role.ADMIN) {
            @Override
            protected Error executionTemplate(String[] args) {
                String dateFormat = "\\d{2}-\\d{2}-\\d{4}";
                if (args[1].matches(dateFormat) && args[2].matches(dateFormat)) {
                    return this.getController().createTournament(args[0], args[1], args[2], args[3], args[4]);
                } else {
                    return Error.INCORRECT_ARGUMENT_FORMAT;
                }
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
                return this.getController().tournamentMatchmake(args[0], args[1], args);
            }
        });
        this.commands.put("tournament-remove", new Command(this.controller, 1, Error.NO_PERMISSION, Role.PLAYER) {
            @Override
            protected Error executionTemplate(String[] args) {
                return this.getController().removeFromTournament(args[0], args.length > 1 ? args[1] : null);
            }
        });
        this.commands.put("load-data", new LoadDataCommand(this.controller));
        this.commands.put("save-data", new SaveDataCommand(this.controller));
    }
}
