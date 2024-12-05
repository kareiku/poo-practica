package org.example.views.commands;

import org.example.controllers.Controller;
import org.example.models.Role;
import org.example.utils.Error;

public class TournamentAddCommand extends Command {
    public TournamentAddCommand(Controller controller) {
        super(controller, 1, Error.NO_PERMISSION, Role.ADMIN);
    }

    protected Error executeTemplate(String[] args) {
        return this.getController().addToTournament(args[0], args.length > 1 ? args[1] : null);
    }
}
