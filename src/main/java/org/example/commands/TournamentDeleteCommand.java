package org.example.commands;

import org.example.models.Command;
import org.example.models.TournamentList;

public class TournamentDeleteCommand extends Command {
    private final TournamentList tournaments;

    public TournamentDeleteCommand(TournamentList tournaments) {
        super(
                "tournament-delete",
                "<name>",
                "Deletes the specified tournament."
        );

        this.tournaments = tournaments;
    }

    public void run(String[] args) {
    }
}
