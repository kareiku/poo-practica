package org.example.views.commands;

import org.example.controllers.Controller;
import org.example.models.Role;
import org.example.utils.Error;

public class TeamDeleteCommand extends Command {
    public TeamDeleteCommand(Controller controller) {
        super(controller, 1, Error.NO_PERMISSION, Role.ADMIN);
    }

    protected Error executeTemplate(String[] args) {
        return this.getController().deleteTeam(args[0]);
    }
}
