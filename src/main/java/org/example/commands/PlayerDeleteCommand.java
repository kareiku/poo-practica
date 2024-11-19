package org.example.commands;

import org.example.models.Command;
import org.example.models.ParticipantSet;

public class PlayerDeleteCommand extends Command {
    private final ParticipantSet participants;

    public PlayerDeleteCommand(ParticipantSet participants) {
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
