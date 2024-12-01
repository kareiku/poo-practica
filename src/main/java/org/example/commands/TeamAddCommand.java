package org.example.commands;

import org.example.models.ParticipantSet;
import org.example.models.Role;
import org.example.views.CommandView;

public class TeamAddCommand extends CommandView implements Command {
    private final ParticipantSet participants;
    public TeamAddCommand(ParticipantSet participants) {
        super("team-add", "<DNI>;<team name>", "Adds the specified player to the specified team.", Role.ADMIN);
        this.participants = participants;
    }

    public void execute() {
        this.participants.add();
    }
}
