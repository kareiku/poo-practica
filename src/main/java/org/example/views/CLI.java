package org.example.views;

import org.example.controllers.CommandFactory;
import org.example.utils.Console;
import org.example.utils.Error;
import org.example.views.commands.Command;

import java.util.ArrayDeque;
import java.util.Deque;

public class CLI {
    private final CommandFactory factory;

    public CLI() {
        this.factory = new CommandFactory();
    }

    public void start() {
        Console.getInstance().println(Message.WELCOME.getMessage());
        String statement;
        do {
            Console.getInstance().print(Message.PROMT.getMessage());
            statement = Console.getInstance().readLine();


            String commandName = statement.split("\\s+", 2)[0];
            Deque<String> args = new ArrayDeque<>();
            args.push(statement.split("\\s+", 2)[0]);
            try {
                String arguments = statement.split("\\s+", 2)[1];
            } catch (ArrayIndexOutOfBoundsException ex) {
                assert false;
            }
            Command command = this.factory.getCommand(args.peek());


            if (command != null) {
                command.execute(args.toArray(new String[0]));
            } else {
                if (!statement.matches("^\\s*exit")) {
                    Console.getInstance().println(Error.COMMAND_NOT_FOUND.getMessage());
                }
            }
        } while (!statement.matches("^\\s*exit"));
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