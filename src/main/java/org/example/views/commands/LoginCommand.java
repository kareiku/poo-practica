package org.example.views.commands;

import org.example.Error;
import org.example.controllers.Controller;
import org.example.models.Role;

public class LoginCommand extends Command {
    public LoginCommand(Controller controller) {
        super(controller);
    }

    protected Role[] templateRoles() {
        return new Role[]{Role.GUEST};
    }

    protected int templateArgumentCount() {
        return 2;
    }

    protected Error templatePermissionError() {
        return Error.ALREADY_LOGGED_ON;
    }

    protected Error templateMethod(String[] args) {
        return this.getController().login(args[0], args[1]);
    }
}
