package es.upm.etsisi.gitlab.commands.admin;

import es.upm.etsisi.gitlab.models.Command;
import es.upm.etsisi.gitlab.models.ParticipantSet;

public class CreateTeamCommand extends Command {
    private final ParticipantSet participants;

    public CreateTeamCommand(ParticipantSet participants) {
        super(
                "team-create",
                "<name>",
                "Creates a team in the system."
        );

        this.participants = participants;
    }

    public void run(String[] args) {
    }
}
