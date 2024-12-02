package org.example.commands;

import org.example.models.Error;
import org.example.models.Role;

public class ExitCommand extends Command {
    private boolean executed;

    public ExitCommand(String name, String usage, String description, Role[] roles) {
        super(name, usage, description, roles);
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
