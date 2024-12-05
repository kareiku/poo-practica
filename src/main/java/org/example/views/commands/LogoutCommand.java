package org.example.views.commands;

import org.example.utils.Error;
import org.example.services.Controller;
import org.example.models.Role;

public class LogoutCommand extends Command {
    public LogoutCommand(Controller controller) {
        super(controller, 0, Error.NOT_LOGGED_ON, Role.ADMIN, Role.PLAYER);
    }

    protected Error executeTemplate(String[] args) {
        return this.getController().logout();
    }
}
