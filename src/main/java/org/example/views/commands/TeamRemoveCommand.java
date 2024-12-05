package org.example.views.commands;

import org.example.controllers.Controller;
import org.example.models.Role;
import org.example.utils.Error;

public class TeamRemoveCommand extends Command {
    public TeamRemoveCommand(Controller controller) {
        super(controller, 0, Error.NO_PERMISSION, Role.PLAYER);
    }

    protected Error executeTemplate(String[] args) {
        return this.getController().removeFromTeam(args[0]);
    }
}
