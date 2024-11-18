package org.example.commands;

import org.example.models.Command;
import org.example.models.ParticipantSet;

public class CreatePlayerCommand extends Command {
    public CreatePlayerCommand(ParticipantSet participants) {
        super(
                "player-create",
                "<DNI>;<name>;<surnames>",
                "Creates a player in the system."
        );
    }

    public void run(String[] args) {
    }
}
