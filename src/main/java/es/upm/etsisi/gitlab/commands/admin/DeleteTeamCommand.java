package es.upm.etsisi.gitlab.commands.admin;

import es.upm.etsisi.gitlab.models.Command;
import es.upm.etsisi.gitlab.models.ParticipantSet;

public class DeleteTeamCommand extends Command {
    private final ParticipantSet participants;

    public DeleteTeamCommand(ParticipantSet participants) {
        super(
                "team-delete",
                "{NAME}",
                "Removes a team from the list of teams"
        );

        this.participants = participants;
    }

    public void run(String[] args) {
    }
}
