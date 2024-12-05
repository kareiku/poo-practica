package org.example.views.commands;

import org.example.controllers.Controller;

public class TournamentCreateCommand extends Command {
    public TournamentCreateCommand(Controller controller) {
        super(controller);
    }

    protected Error executeTemplate(String[] args) {
    }
}
