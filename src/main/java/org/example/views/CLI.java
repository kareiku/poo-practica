package org.example.views;

import org.example.controllers.CommandFactory;
import org.example.utils.Console;
import org.example.utils.Error;
import org.example.views.commands.Command;
import org.example.views.commands.CommandToExit;

public class SportsManagementSystem {
    public static void main(String[] args) {
        new CLI().start();
    }

    private static class CLI {
        private final CommandFactory factory;

        private CLI() {
            this.factory = new CommandFactory();
        }

        private void start() {
            Console.getInstance().println(Message.WELCOME.getMessage());
            String statement;
            do {
                Console.getInstance().print(Message.PROMT.getMessage());
                statement = Console.getInstance().readLine();


                String[] parts = statement.split("\\s+", 2);
                String commandName = parts[0];
                String[] args = parts[1] != null ? parts[1].split(";") : null;


                try {
                    String arguments = statement.split("\\s+", 2)[1];
                } catch (ArrayIndexOutOfBoundsException ex) {
                    assert false;
                }
                Command command = this.factory.getCommand(commandName);


                if (command != null) {
                    command.execute(args);
                } else {
                    Console.getInstance().println(Error.COMMAND_NOT_FOUND.getMessage());
                }
            } while (!(((CommandToExit) factory.getCommand("exit")).hasBeenExecuted()));
            Console.getInstance().println(Message.BYE.getMessage());
        }

        private enum Message {
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
    }
}