package es.upm.etsisi.gitlab.commands.admin;

import es.upm.etsisi.gitlab.models.Command;
import es.upm.etsisi.gitlab.models.ParticipantSet;

public class CreatePlayerCommand extends Command {
    public CreatePlayerCommand(ParticipantSet participants) {
        super(
                "player-create",
                "{DNI;NAME;SURNAMES}",
                "Creates a player in the system."
        );
    }

    public void run(String[] args) {
    }
}
