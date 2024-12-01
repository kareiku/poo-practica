package org.example.commands;

import org.example.models.Participant;
import org.example.models.ParticipantSet;
import org.example.models.Player;
import org.example.models.Role;
import org.example.views.CommandView;

import java.util.Iterator;

public class PlayerDeleteCommand extends CommandView implements Command {
    private final ParticipantSet participants;

    public PlayerDeleteCommand(ParticipantSet participants) {
        super("player-delete", "<DNI>", "Removes a player from the system.", Role.ADMIN);
        this.participants = participants;
    }

    private void execute(Player player) {
        Iterator<Participant> iterator = participants.iterator();
        while (iterator.hasNext()) {
            Participant participant = iterator.next();
            if (participant.equals(player) && ((Player) participant).equals(player)) { // fixme huh? cast?
                participants.remove(player);
            }
        }
    }
}
