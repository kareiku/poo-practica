package org.example.commands;

import org.example.models.Command;
import org.example.models.ParticipantSet;
import org.example.models.TournamentList;

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
