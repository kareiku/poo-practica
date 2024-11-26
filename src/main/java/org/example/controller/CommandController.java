package org.example.controller;

import org.example.CommandCreator;
import org.example.views.Command;

import java.util.Map;

public final class CommandController {
    private final Map<String, Command> commands;

    public CommandController() {
        this.commands = new CommandCreator().createCommands();
    }

    public Map<String, Command> getCommands() {
        return this.commands;
    }

    public void showHelp() {
        commands.forEach((commandName, command) -> command.showHelp());
    }
}
