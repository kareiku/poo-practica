package es.upm.etsisi.gitlab.commands.admin;

import es.upm.etsisi.gitlab.models.Command;
import es.upm.etsisi.gitlab.models.ParticipantSet;
import es.upm.etsisi.gitlab.models.TournamentList;

public class TournamentMatchmakingCommand extends Command {
    private final ParticipantSet participants;
    private final TournamentList tournaments;

    public TournamentMatchmakingCommand(ParticipantSet participants, TournamentList tournaments) {
        super(
                "tournament-matchmaking",
                "", // TODO
                ""  // TODO
        );

        this.participants = participants;
        this.tournaments = tournaments;
    }

    public void run(String[] args) {
    }
}
