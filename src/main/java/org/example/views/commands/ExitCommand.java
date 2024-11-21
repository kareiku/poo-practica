package org.example.views.commands;

import org.example.views.Command;

public class ExitCommand extends Command {
    private boolean executed;

    public void execute(String[] args) {
        executed = true;
    }

    public String name() {
        return "exit";
    }

    public String usage() {
        return "";
    }

    public String help() {
        return "Exits the application, regardless of the current state. It does save changes.";
    }

    public boolean hasBeenExecuted() {
        return executed;
    }
}
