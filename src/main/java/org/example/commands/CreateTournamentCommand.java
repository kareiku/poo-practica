package org.example.commands;

import org.example.models.Command;
import org.example.models.TournamentList;

public class CreateTournamentCommand extends Command {
    private final TournamentList tournaments;
    public CreateTournamentCommand(TournamentList tournaments) {
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
