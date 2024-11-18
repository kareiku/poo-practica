package org.example.commands;

import org.example.models.Command;

public class ExitCommand extends Command {
    private boolean executed;

    public ExitCommand() {
        super(
                "exit",
                "",
                "Exits the application, regardless of the current state. It does save changes."
        );
    }

    public void run(String[] args) {
        executed = true;
    }

    public boolean hasBeenExecuted() {
        return executed;
    }
}
