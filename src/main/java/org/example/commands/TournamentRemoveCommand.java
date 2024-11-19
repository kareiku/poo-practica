package org.example.commands;

import org.example.models.Command;
import org.example.models.ParticipantSet;
import org.example.models.TournamentList;

public class TournamentRemoveCommand extends Command {
    private final ParticipantSet participants;
    private final TournamentList tournaments;

    public TournamentRemoveCommand(ParticipantSet participants, TournamentList tournaments) {
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