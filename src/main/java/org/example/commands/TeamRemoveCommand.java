package org.example.commands;

import org.example.models.Command;
import org.example.models.ParticipantSet;

public class TeamRemoveCommand extends Command {
    private final ParticipantSet participants;

    public TeamRemoveCommand(ParticipantSet participants) {
        super(
                "team-remove",
                "<DNI>",
                "Removes the specified player from the specified team."
        );

        this.participants = participants;
    }

    public void run(String[] args) {
    }
}
