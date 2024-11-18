package es.upm.etsisi.gitlab.commands.player;

import es.upm.etsisi.gitlab.models.Command;
import es.upm.etsisi.gitlab.models.ParticipantSet;
import es.upm.etsisi.gitlab.models.TournamentList;

public class RemoveCurrentFromTournamentCommand extends Command {
    private final ParticipantSet participants;
    private final TournamentList tournaments;

    public RemoveCurrentFromTournamentCommand(ParticipantSet participants, TournamentList tournaments) {
        super(
                "tournament-remove",
                "<tournament name>",
                "Deletes a tournament, regardless of its current state."
        );

        this.participants = participants;
        this.tournaments = tournaments;
    }

    public void run(String[] args) {
    }
}
