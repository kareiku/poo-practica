package org.example.commands;

import org.example.models.Command;
import org.example.models.TournamentList;

public class TournamentCreateCommand extends Command {
    private final TournamentList tournaments;
    public TournamentCreateCommand(TournamentList tournaments) {
        super(
                "tournament-create",
                "<name>;<start date>;<end date>;<league>;<sport>}",
                "Creates a tournament."
        );

        this.tournaments = tournaments;
    }

    public void run(String[] args) {
    }
}
