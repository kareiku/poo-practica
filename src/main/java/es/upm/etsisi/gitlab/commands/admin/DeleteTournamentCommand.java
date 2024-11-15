package es.upm.etsisi.gitlab.commands.admin;

import es.upm.etsisi.gitlab.models.Command;
import es.upm.etsisi.gitlab.models.TournamentList;

public class DeleteTournamentCommand extends Command {
    private final TournamentList tournaments;

    public DeleteTournamentCommand(TournamentList tournaments) {
        super(
                "tournament-delete",
                "{NAME}",
                "Deletes the specified tournament."
        );

        this.tournaments = tournaments;
    }

    public void run(String[] args) {
    }
}
