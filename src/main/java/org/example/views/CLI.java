package org.example.views;

import org.example.views.commands.*;
import org.example.controller.Logger;
import org.example.models.Error;
import org.example.models.Role;
import org.example.models.User;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class CLI {
    private final Map<String, Command> commands;
    private User currentUser;

    public CLI() {
        this.commands = new HashMap<>();
        this.putCommands();
        this.currentUser = new User(Role.GUEST);
    }

    public void start() {
        Logger logger = null;
        try {
            logger = new Logger("../../../../resources/logs");
            this.readUntilExit(logger);
        } catch (IOException ex) {
            System.err.println("Error when opening logger. This session's log won't be saved.");
        } finally {
            if (logger != null) {
                logger.close();
            }
        }
    }

    private void readUntilExit(Logger logger) throws IOException {
        Message.WELCOME.write();
        do {
            Message.INPUT_LINE.write();
            String statement = new Scanner(System.in).nextLine();
            this.scan(statement);
            logger.log(statement);
        } while (!((ExitCommand) commands.get("exit")).hasBeenExecuted());
        Message.BYE.write();
    }

    private void scan(String statement) {
        String commandName = statement.split("\\s+")[0];
        String[] args = statement.split("\\s+")[1].split(";");
        Command commandToRun = this.selectCommand(commandName);
        if (commandToRun != null) {
            if (commandToRun.hasPermission(currentUser)) {
                commandToRun.execute(args);
            } else {
                org.example.models.Error.NO_PERMISSION.write();
            }
        } else {
            Error.UNKNOWN_COMMAND_ERROR.write();
        }
    }

    private Command selectCommand(String commandName) {
        Command commandToRun = null;
        boolean commandFound = false;
        for (String commandID : commands.keySet()) {
            if (!commandFound && (commandFound = commandID.equals(commandName))) { // Fixme. Pretty illegible, isn't it?
                commandToRun = commands.get(commandID);
            }
        }
        return commandToRun;
    }

    private void putCommands() {
        final Command[] commands = {
                new ExitCommand(),
                new HelpCommand(null), // Fixme. The fuck is a null as parameter doing here?
                new LoginCommand(),
                new LogoutCommand(),
                new PlayerCreateCommand(),
                new PlayerDeleteCommand(),
                new StatisticsShowCommand(),
                new TeamAddCommand(),
                new TeamCreateCommand(),
                new TeamDeleteCommand(),
                new TeamRemoveCommand(),
                new TournamentAddCommand(),
                new TournamentCreateCommand(),
                new TournamentDeleteCommand(),
                new TournamentListCommand(),
                new TournamentMatchmakingCommand(),
                new TournamentRemoveCommand()
        };
        for (Command command : commands) {
            this.commands.put(command.name(), command);
        }
    }
}
