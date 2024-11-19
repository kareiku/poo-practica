package org.example.views;

import org.example.commands.*;
import org.example.drivers.Logger;
import org.example.models.*;

import java.io.IOException;
import java.util.*;

public class CLI {
    /* Commands is a HashMap where each Command has a privilege level, designated by an Integer, alike Unix does.
     * 0:
     * 1:
     */
    private final Map<Command, Integer> commands;

    public CLI() {
        this.commands = new HashMap<>();
        this.putCommands();
    }

    public void start() {
        try (Logger logger = new Logger("../../../../resources/logs")) {
            this.readUntilExit(logger);
        } catch (IOException ex) {
            System.err.println("Error when opening logger. This session's log won't be saved.");
        }
    }

    private void readUntilExit(Logger logger) throws IOException {
        Message.WELCOME.write();
        do {
            Message.INPUT_LINE.write();
            String statement = new Scanner(System.in).nextLine();
            this.scan(statement);
            logger.log(statement);
        } while (!exitCommand.hasBeenExecuted());
        Message.BYE.write();
    }

    // fixme
    private void scan(String statement) {
        String commandName = statement.split("\\s+")[0];
        String[] args = statement.split("\\s+")[1].split(";");
        for (Role role : commandMap.keySet()) {
            if (loginCommand.getRole().hasPermission(role)) {
                allowedCommands.addAll(commandMap.get(role));
            }
        }
        // unfinished here


        Command command = null;
        Iterator<Command> commandIterator = allowedCommands.iterator();
        boolean found = false;

        while (!found && commandIterator.hasNext()) {
            command = commandIterator.next();
            found = command.matches(commandName);
        }

        if (found) {
            command.execute(args);
        } else {
            Error.UNKNOWN_COMMAND_ERROR.write();
        }
    }

    // fixme
    private List<Command> createAdminCommandList() {
        return new ArrayList<>(Arrays.asList(
                new TeamAddCommand(participants),
                new PlayerCreateCommand(participants),
                new TeamCreateCommand(participants),
                new TournamentCreateCommand(tournaments),
                new PlayerDeleteCommand(participants),
                new TeamDeleteCommand(participants),
                new TournamentDeleteCommand(tournaments),
                new TeamRemoveCommand(participants),
                new TournamentMatchmakingCommand(participants, tournaments)
        ));
    }

    // fixme
    private List<Command> createPlayerCommandList() {
        return new ArrayList<>(Arrays.asList(
                new TournamentAddCommand(participants, tournaments),
                new TournamentRemoveCommand(participants, tournaments),
                new StatisticsShowCommand(participants)
        ));
    }

    // fixme
    private List<Command> createUserCommandList() {
        return new ArrayList<>(Arrays.asList(
                new ExitCommand(),
                new LoginCommand(),
                new LogoutCommand(),
                new TournamentListCommand(loginCommand.getRole(), tournaments)
        ));
    }

    private void putCommands() {
        Command[] commands = {
                new ExitCommand(),
                new HelpCommand(),
                new LoginCommand(),
                new LogoutCommand(),
        };

        for (Command command : commands) {
            this.commands.put(command, command.privilegeLevel());
        }
    }
}
