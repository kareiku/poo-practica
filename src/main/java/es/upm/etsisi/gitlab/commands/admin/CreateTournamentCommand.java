package es.upm.etsisi.gitlab.commands.admin;

import es.upm.etsisi.gitlab.models.Command;
import es.upm.etsisi.gitlab.models.TournamentList;

public class CreateTournamentCommand extends Command {
    private final TournamentList tournaments;
    public CreateTournamentCommand(TournamentList tournaments) {
        super(
                "tournament-create",
                "{NAME;START_DATE;END_DATE;LEAGUE;SPORT}",
                "Creates a tournament."
        );

        this.tournaments = tournaments;
    }

    public void run(String[] args) {
    }
}
