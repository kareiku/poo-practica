package org.example.views.commands;

import org.example.Error;

public class ExitCommand extends Command {
    private boolean executed;

    public ExitCommand(String name, String usage, String description) {
        super(name, usage, description);
        this.executed = false;
    }

    public Error execute(String[] args) {
        this.executed = true;
        return Error.NONE;
    }

    public boolean hasBeenExecuted() {
        return this.executed;
    }
}
