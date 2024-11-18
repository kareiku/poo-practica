package org.example.commands;

import org.example.models.Command;
import org.example.models.ParticipantSet;
import org.example.models.TournamentList;

public class AddCurrentToTournamentCommand extends Command {
    private final ParticipantSet participants;
    private final TournamentList tournaments;

    public AddCurrentToTournamentCommand(ParticipantSet participants, TournamentList tournaments) {
        super(
                "tournament-add",
                "<DNI | team name>;<tournament name>",
                "Adds a player or a team to a tournament."
        );

        this.participants = participants;
        this.tournaments = tournaments;
    }

    public void run(String[] args) {
    }
}
