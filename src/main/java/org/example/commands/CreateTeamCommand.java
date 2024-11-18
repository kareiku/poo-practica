package org.example.commands;

import org.example.models.Command;
import org.example.models.ParticipantSet;

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
