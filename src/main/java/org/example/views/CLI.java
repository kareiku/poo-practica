package org.example.views;

import org.example.controllers.CommandFactory;
import org.example.utils.Console;
import org.example.utils.Error;
import org.example.views.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class CLI {
    private final CommandFactory factory;
    private final MessageView messageView;

    private CLI() {
        this.factory = new CommandFactory();
        this.messageView = new MessageView();
    }

    private void start() {
        String statement;
        this.messageView.println(Message.WELCOME);
        do {
            this.messageView.println(Message.PROMT);
            statement = Console.getInstance().readLine();
            String commandName = statement.split("\\s+", 2)[0];
            String[] args = statement.split("\\s+", 2)[1].split((";")); // fixme possible ArrayOutOfBoundsException?
            Command command = factory.getCommand(commandName);
            if (command != null) {
                command.execute(args);
            } else {
                Console.getInstance().println(Error.COMMAND_NOT_FOUND.getMessage());
            }
        } while (!statement.equalsIgnoreCase("exit"));
        this.messageView.println(Message.BYE);
    }

    public static void main(String[] args) {
        new CLI().start();
    }
}

enum Message {
    WELCOME, PROMT, BYE
}

class MessageView {
    private final Map<Message, String> messages;

    MessageView() {
        this.messages = new HashMap<>();
        this.associateMessages();
    }

    void println(Message message) {
        Console.getInstance().println(messages.get(message));
    }

    private void associateMessages() {
        this.messages.putIfAbsent(Message.WELCOME, "Welcome to the Sport's Management System.\n" +
                "Write a command to start using the app.\n" +
                "If unsure, use the \"help\" command.");
        this.messages.putIfAbsent(Message.PROMT, "> ");
        this.messages.putIfAbsent(Message.BYE, "Exiting the application...");
    }
}