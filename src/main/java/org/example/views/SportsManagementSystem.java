package org.example.views;

import org.example.controllers.CommandFactory;
import org.example.utils.Console;
import org.example.utils.Error;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SportsManagementSystem {
    public static void main(String[] args) {
        new CLI().start();
    }

    private static class CLI {
        private final CommandFactory factory;

        CLI() {
            this.factory = new CommandFactory();
        }

        void start() {
            try (PrintWriter writer = new PrintWriter(new FileWriter(new File("").getAbsolutePath() + "/src/main/resources/logs/" + Instant.now() + ".log"))) {
                Console.getInstance().println(Message.WELCOME.getMessage());
                String statement;
                do {
                    Console.getInstance().print(Message.PROMT.getMessage());
                    statement = Console.getInstance().readLine().trim();
                    writer.printf("[%s] %s%n", Instant.now(), statement);
                    Optional<Command> optional = this.factory.getOptionalCommand(statement.split("\\s+")[0]);
                    String[] args = this.getArguments(statement);
                    optional.ifPresentOrElse(
                            command -> command.execute(args),
                            () -> Console.getInstance().println(Error.COMMAND_NOT_FOUND.getMessage())
                    );
                } while (this.factory.getOptionalCommand("exit").isPresent());
                Console.getInstance().println(Message.BYE.getMessage());
            } catch (IOException ex) {
                assert false : ex.getMessage();
            }
        }

        private String[] getArguments(String statement) {
            List<String> args = new ArrayList<>();
            String[] parts = statement.split("\\s+", 2);
            if (parts.length > 1) {
                String[] arguments = parts[1].split(";");
                args.addAll(Arrays.asList(arguments));
            }
            return args.toArray(new String[0]);
        }

        private enum Message {
            WELCOME("Welcome to the Sports' Management System."),
            PROMT("SportsManagementSystem:~$ "),
            BYE("Bye");
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