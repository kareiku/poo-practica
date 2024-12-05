package org.example.views.commands;

import org.example.utils.Error;
import org.example.services.Controller;
import org.example.models.Role;

public class PlayerCreateCommand extends Command {
    public PlayerCreateCommand(Controller controller) {
        super(controller, 3, Error.NO_PERMISSION, Role.ADMIN);
    }

    protected Error executeTemplate(String[] args) {
        Error error = Error.INCORRECT_ARGUMENT_FORMAT;
        if (args[2].matches("^\\d{8}[A-Za-z]$")) {
            error = this.getController().createPlayer(args[0], args[1], args[2]);
        }
        return error;
    }
}
