package org.example.views;

import org.example.controllers.Controller;
import org.example.models.Error;
import org.example.models.Message;

import java.util.Scanner;

public class CLI {
    private final Controller controller = new Controller(); // fixme (question) Do I really need to make empty constructors for little things like this?

    public void start() {
        System.out.println(Message.WELCOME.message);
        do {
            System.out.print(Message.INPUT_LINE.message); // fixme Being message completely static... is it okay to have public attributes?
            this.controller.handleInput(new Scanner(System.in).nextLine());
        } while (!this.controller.exitHasBeenExecuted());
        System.out.println(Message.BYE.message);
    }

    @Deprecated // fixme delegate to Controller
    private void scan(String statement) {
        String commandName = statement.split("\\s+")[0];
        String[] args = statement.split("\\s+", 2)[1].split(";");
        CommandView commandToRun = this.selectCommand(commandName);
        if (commandToRun != null) {
            if (commandToRun.hasPermission(currentUser)) {
                commandToRun.execute(args);
            } else {
                Error.NO_PERMISSION.write();
            }
        } else {
            Error.UNKNOWN_COMMAND_ERROR.write();
        }
    }

    @Deprecated // fixme delegate to Controller
    private CommandView selectCommand(String commandName) {
        CommandView commandToRun = null;
        boolean commandFound = false;
        for (String commandID : commands.keySet()) {
            if (!commandFound) {
                commandToRun = commands.get(commandID);
                commandFound = commandID.equals(commandName);
            }
        }
        return commandToRun;
    }
}
