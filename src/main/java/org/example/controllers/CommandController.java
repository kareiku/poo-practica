package org.example.controllers;

import org.example.commands.Command;
import org.example.views.CommandView;

public class CommandController {
    public void parseCommand(String statement) {
    }

    public void showHelp() {
        this.commands.forEach((commandName, command) -> new CommandView().showHelp(command));
    }

    public void execute(Command command) {
    }
}
