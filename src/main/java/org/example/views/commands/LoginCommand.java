package org.example.views.commands;

import org.example.controllers.Controller;
import org.example.models.Role;
import org.example.utils.Error;

public class LoginCommand extends Command {
    public LoginCommand(Controller controller) {
        super(controller, 2, Error.ALREADY_LOGGED_ON, Role.GUEST);
    }

    protected Error executeTemplate(String[] args) {
        return this.getController().login(args[0], args[1]);
    }
}
