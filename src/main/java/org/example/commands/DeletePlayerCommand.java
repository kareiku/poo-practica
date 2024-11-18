package es.upm.etsisi.gitlab.commands.admin;

import es.upm.etsisi.gitlab.models.Command;
import es.upm.etsisi.gitlab.models.ParticipantSet;

public class DeletePlayerCommand extends Command {
    private final ParticipantSet participants;

    public DeletePlayerCommand(ParticipantSet participants) {
        super(
                "player-delete",
                "<DNI>",
                "Removes a player from the system"
        );

        this.participants = participants;
    }

    public void run(String[] args) {
    }
}
