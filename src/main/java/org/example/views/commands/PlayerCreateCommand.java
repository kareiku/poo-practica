package org.example.views.commands;

import org.example.Error;
import org.example.controllers.Controller;
import org.example.models.Role;

public class PlayerCreateCommand extends Command {
    public PlayerCreateCommand(Controller controller) {
        super(controller);
    }

    protected Role[] templateRoles() {
        return new Role[]{Role.ADMIN};
    }

    protected int templateArgumentCount() {
        return 3;
    }

    protected Error templatePermissionError() {
        return Error.NO_PERMISSION;
    }

    protected Error templateMethod(String[] args) {
        Error error = Error.INCORRECT_ARGUMENT_FORMAT;
        if (args[2].matches("^\\d{8}[A-Za-z]$")) {
            error = this.getController().addPlayer(args[0], args[1], args[2]);
        }
        return error;
    }
}
