package org.example.views.commands;

import org.example.utils.Error;
import org.example.controllers.Controller;
import org.example.models.Role;

public class PlayerDeleteCommand extends Command {
    public PlayerDeleteCommand(Controller controller) {
        super(controller, 1, Error.NO_PERMISSION, Role.ADMIN);
    }

    protected Error executeTemplate(String[] args) {
        Error error = Error.INCORRECT_ARGUMENT_FORMAT;
        if (args[0].matches("^\\d{8}[A-Za-z]$")) {
            // fixme: A player musn't be deleted if it's participating in a currently-in-progress tournament.
            error = this.getController().deletePlayer(args[0]);
        }
        return error;
    }
}
