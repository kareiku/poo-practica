package org.example.views.commands;

import org.example.models.Error;

public class TeamDeleteCommand extends Command {
    public TeamDeleteCommand(String name, String usage, String description) {
        super(name, usage, description);
    }

    public Error execute(String[] args) {
        return Error.NONE;
    }
}
