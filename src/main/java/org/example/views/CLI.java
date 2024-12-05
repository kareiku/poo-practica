package org.example.views;

import org.example.controllers.CommandFactory;
import org.example.utils.Console;
import org.example.utils.Error;
import org.example.views.commands.Command;

public class CLI {
    private final CommandFactory factory;

    public CLI() {
        this.factory = new CommandFactory();
    }

    public void start() {
        String statement;
        Console.getInstance().println(Message.WELCOME.getMessage());
        do {
            Console.getInstance().print(Message.PROMT.getMessage());
            statement = Console.getInstance().readLine();
            String commandName = statement.split("\\s+", 2)[0];
            String[] args = statement.split("\\s+", 2)[1].split((";")); // fixme possible ArrayOutOfBoundsException?
            Command command = this.factory.getCommand(commandName);
            if (command != null) {
                command.execute(args);
            } else {
                Console.getInstance().println(Error.COMMAND_NOT_FOUND.getMessage());
            }
        } while (!statement.equals("exit"));
        Console.getInstance().println(Message.BYE.getMessage());
    }

    public static void main(String[] args) {
        new CLI().start();
    }
}

enum Message {
    WELCOME("Welcome to the Sport's Management System.\n" +
            "Write a command to start using the app.\n" +
            "If unsure, use the \"help\" command."),
    PROMT("> "),
    BYE("Exiting the application...");
    private final String message;

    Message(String message) {
        this.message = message;
    }

    String getMessage() {
        return this.message;
    }
}