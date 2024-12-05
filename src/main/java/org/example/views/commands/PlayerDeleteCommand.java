package org.example.views.commands;

import org.example.utils.Error;
import org.example.services.Controller;
import org.example.models.Role;

public class PlayerDeleteCommand extends Command {
    public PlayerDeleteCommand(Controller controller) {
        super(controller, 1, Error.NO_PERMISSION, Role.ADMIN);
    }

    protected Error executeTemplate(String[] args) {
        Error error = Error.INCORRECT_ARGUMENT_FORMAT;
        if (args[0].matches("^\\d{8}[A-Za-z]$")) {
            error = this.getController().deletePlayer(args[0]);
        }
        return error;
    }
}
