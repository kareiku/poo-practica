package org.example.views.commands;

import org.example.Error;
import org.example.controllers.Controller;
import org.example.models.Role;

public class StatisticsShowCommand extends Command {
    public StatisticsShowCommand(Controller controller) {
        super(controller);
    }

    protected Role[] templateRoles() {
        return new Role[]{Role.PLAYER};
    }

    protected int templateArgumentCount() {
        return 0;
    }

    protected Error templatePermissionError() {
        return Error.NO_PERMISSION;
    }

    protected Error templateMethod(String[] args) {
        return null; // TODO
    }
}
