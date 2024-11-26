package org.example.views.commands;

import org.example.models.Role;
import org.example.views.Command;

public class ExitCommand extends Command {
    private boolean executed;

    public ExitCommand() {
        super("exit", "", "Exits the application, regardless of the current state. It does save changes.", Role.GUEST);
        this.executed = false;
    }

    public void execute(String[] args) {
        this.executed = true;
    }

    public boolean hasBeenExecuted() {
        return this.executed;
    }
}
