package org.example.views.commands;

import org.example.controllers.Controller;
import org.example.models.Role;
import org.example.utils.Error;

public class TournamentListCommand extends Command {
    public TournamentListCommand(Controller controller) {
        super(controller);
    }

    protected Error executeTemplate(String[] args) {
    }
}
