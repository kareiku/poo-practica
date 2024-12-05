package org.example.views.commands;

import org.example.controllers.Controller;
import org.example.models.Role;
import org.example.utils.Error;

public class PlayerDeleteCommand extends Command {
    public PlayerDeleteCommand(Controller controller) {
        super(controller, 1, Error.NO_PERMISSION, Role.ADMIN);
    }

    protected Error executeTemplate(String[] args) {
        Error error = Error.INCORRECT_ARGUMENT_FORMAT;
        if (args[0].matches("^\\d{8}[A-Za-z]$")) {
            if (!this.getController().isPlayerParticipatingInAInProgressTournament(args[0])) {
                error = this.getController().deletePlayer(args[0]);
            } else {
                error = Error.PARTICIPANT_ON_TOURNAMENT_IN_PROGRESS;
            }
        }
        return error;
    }
}
