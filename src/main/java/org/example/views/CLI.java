package org.example.views;

import org.example.controllers.CommandFactory;
import org.example.utils.Console;
import org.example.utils.Error;
import org.example.views.commands.Command;
import org.example.views.commands.CommandToExit;

import java.util.Deque;
import java.util.LinkedList;

public class CLI {
    private final CommandFactory factory;

    private CLI() {
        this.factory = new CommandFactory();
    }

    public static void main(String[] args) {
        new CLI().start();
    }

    private void start() {
        Console.getInstance().println(Message.WELCOME.getMessage());
        String statement;
        do {
            Console.getInstance().print(Message.PROMT.getMessage());
            statement = Console.getInstance().readLine().trim();
            Command command = this.getCommand(statement);
            String[] args = this.getArguments(statement);
            if (command != null) {
                command.execute(args);
            } else {
                Console.getInstance().println(Error.COMMAND_NOT_FOUND.getMessage());
            }
        } while (!(((CommandToExit) factory.getCommand("exit")).hasBeenExecuted()));
        Console.getInstance().println(Message.BYE.getMessage());
    }

    private Command getCommand(String statement) {
        return this.factory.getCommand(statement.split("\\s+")[0]);
    }

    private String[] getArguments(String statement) {
        Deque<String> args = new LinkedList<>();
        String[] parts = statement.split("\\s+");
        if (parts.length > 1) {
            String[] arguments = parts[1].split(";");
            for (String argument : arguments) {
                args.push(argument);
            }
        }
        return args.toArray(new String[0]);
    }

    private enum Message {
        WELCOME("Welcome to the Sport's Management System."),
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
}