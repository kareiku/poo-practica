package org.example.commands;

import org.example.models.ParticipantSet;
import org.example.models.Player;
import org.example.views.CommandView;
import org.example.models.Role;

public class PlayerCreateCommand extends CommandView implements Command {
    private final ParticipantSet participants;

    public PlayerCreateCommand(ParticipantSet participants) {
        super("player-create", "<DNI>;<name>;<surnames>", "Creates a player in the system.", Role.ADMIN);
        this.participants = participants;
    }

    public void execute(String[] args) {
        participants.add(new Player(args[0], args[1], args[2], args[3], args[4]));
    }
}
