package org.example.commands;

import org.example.models.Command;
import org.example.models.ParticipantSet;

public class TeamDeleteCommand extends Command {
    private final ParticipantSet participants;

    public TeamDeleteCommand(ParticipantSet participants) {
        super(
                "team-delete",
                "<name>",
                "Removes a team from the list of teams"
        );

        this.participants = participants;
    }

    public void run(String[] args) {
    }
}
