package org.example.views.commands;

import org.example.controllers.Controller;
import org.example.models.Role;
import org.example.utils.Error;

public class TeamAddCommand extends Command {
    public TeamAddCommand(Controller controller) {
        super(controller, 2, Error.NO_PERMISSION, Role.ADMIN);
    }

    protected Error executeTemplate(String[] args) {
        if (args[0].matches("^\\d+{8}[A-Za-z]$") && args[1].matches("[A-Za-z]+")) {
            return this.getController().addToTeam(args[0], args[1]);
        } else {
            return Error.INCORRECT_ARGUMENT_FORMAT;
        }
    }
}
