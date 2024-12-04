package org.example.views.commands;

import org.example.Error;
import org.example.controllers.Controller;
import org.example.models.Role;

public class LogoutCommand extends Command {
    public LogoutCommand(Controller controller) {
        super(controller);
    }

    protected Role[] templateRoles() {
        return new Role[]{Role.ADMIN, Role.PLAYER};
    }

    protected int templateArgumentCount() {
        return 0;
    }

    protected Error templatePermissionError() {
        return Error.NOT_LOGGED_ON;
    }

    protected Error templateMethod(String[] args) {
        return this.getController().logout();
    }
}
