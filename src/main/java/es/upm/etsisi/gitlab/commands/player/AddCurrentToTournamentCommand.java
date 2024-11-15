package es.upm.etsisi.gitlab.commands.player;

import es.upm.etsisi.gitlab.models.Command;
import es.upm.etsisi.gitlab.models.ParticipantSet;
import es.upm.etsisi.gitlab.models.TournamentList;

public class AddCurrentToTournamentCommand extends Command {
    private final ParticipantSet participants;
    private final TournamentList tournaments;

    public AddCurrentToTournamentCommand(ParticipantSet participants, TournamentList tournaments) {
        super(
                "tournament-add",
                "{PLAYER_DNI|TEAM_NAME;TOURNAMENT_NAME}",
                "Adds a player or a team to a tournament."
        );

        this.participants = participants;
        this.tournaments = tournaments;
    }

    public void run(String[] args) {
    }
}
