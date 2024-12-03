package org.example.views.commands;

import org.example.models.Error;

public class TournamentDeleteCommand extends Command {
    public TournamentDeleteCommand(String name, String usage, String description) {
        super(name, usage, description);
    }

    public Error execute(String[] args) {
        return Error.NONE;
    }
}