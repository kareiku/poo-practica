package org.example.commands;

import org.example.models.Command;
import org.example.models.ParticipantSet;

public class TeamAddCommand extends Command {
    public TeamAddCommand(ParticipantSet participants) {
        super(
                "team-add",
                "<DNI>;<team name>",
                "Adds the specified player to the specified team."
        );
    }

    public void run(String[] args) {
        /* TODO
         *  - Search for player
         *  - Search for team
         *  - If both exist, add player to team
         */
    }
}
