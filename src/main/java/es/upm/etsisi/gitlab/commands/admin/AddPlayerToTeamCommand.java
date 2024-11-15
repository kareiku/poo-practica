package es.upm.etsisi.gitlab.commands.admin;

import es.upm.etsisi.gitlab.models.Command;
import es.upm.etsisi.gitlab.models.ParticipantSet;

public class AddPlayerToTeamCommand extends Command {
    public AddPlayerToTeamCommand(ParticipantSet participants) {
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
