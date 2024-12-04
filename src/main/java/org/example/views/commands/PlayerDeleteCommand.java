package org.example.views.commands;

import org.example.Error;
import org.example.controllers.Controller;
import org.example.models.Role;

public class PlayerDeleteCommand extends Command {
    public PlayerDeleteCommand(Controller controller) {
        super(controller);
    }

    protected Role[] templateRoles() {
        return new Role[]{Role.ADMIN};
    }

    protected int templateArgumentCount() {
        return 1;
    }

    protected Error templatePermissionError() {
        return Error.NO_PERMISSION;
    }

    protected Error templateMethod(String[] args) {
        return this.getController().deletePlayer(args[0]);
    }
}
