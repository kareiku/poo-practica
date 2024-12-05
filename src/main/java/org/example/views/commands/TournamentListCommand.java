package org.example.views.commands;

import org.example.controllers.Controller;
import org.example.models.Role;
import org.example.utils.Console;
import org.example.utils.Error;

public class TournamentListCommand extends Command {
    public TournamentListCommand(Controller controller) {
        super(controller, 0, Error.NO_PERMISSION, Role.ADMIN, Role.PLAYER, Role.GUEST);
    }

    protected Error executeTemplate(String[] args) {
        Console.getInstance().println(this.getController().listTournaments());
        return Error.NONE;
    }
}
