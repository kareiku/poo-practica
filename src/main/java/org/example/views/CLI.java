package org.example.views;

import org.example.controller.CommandController;
import org.example.controller.Logger;
import org.example.models.Error;
import org.example.models.Role;
import org.example.models.User;
import org.example.views.commands.ExitCommand;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public final class CLI {
    private final Map<String, Command> commands;
    private User currentUser;

    public CLI() {
        this.commands = new CommandController().getCommands();
        this.currentUser = new User(Role.GUEST);
    }

    public void start() {
        Logger logger = null;
        try {
            logger = new Logger("../../../../resources/logs");
            this.readUntilExit(logger);
        } catch (IOException ex) {
            System.err.println("Error when logging the statement. Exiting now...");
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
        String[] args = statement.split("\\s+", 2)[1].split(";");
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
}
