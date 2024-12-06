package org.example.views.commands;

import org.example.controllers.Controller;
import org.example.models.Role;
import org.example.utils.Error;

public class LogoutCommand extends Command {
    public LogoutCommand(Controller controller) {
        super(controller, 0, Error.NOT_LOGGED_ON, Role.ADMIN, Role.PLAYER);
    }

    protected Error executeTemplate(String[] args) {
        this.getController().logout();
        return Error.NONE;
    }
}
