package org.example.commands;

import org.example.models.Participant;
import org.example.models.Player;

import java.util.Iterator;

public class PlayerDeleteCommand extends Command {
    public PlayerDeleteCommand(String name, String usage, String description) {
        super(name, usage, description);
    }

    public void execute(String[] args) {
        Iterator<Participant> iterator = participants.iterator();
        while (iterator.hasNext()) {
            Participant participant = iterator.next();
            if (participant.equals(player) && ((Player) participant).equals(player)) { // fixme huh? cast?
                participants.remove(player);
            }
        }
    }
}
