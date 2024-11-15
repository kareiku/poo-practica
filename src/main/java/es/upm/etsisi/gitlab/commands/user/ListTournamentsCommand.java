package es.upm.etsisi.gitlab.commands.user;

import es.upm.etsisi.gitlab.models.Command;
import es.upm.etsisi.gitlab.models.Role;
import es.upm.etsisi.gitlab.models.TournamentList;

public class ListTournamentsCommand extends Command {
    private Role role;
    private final TournamentList tournaments;

    public ListTournamentsCommand(Role role, TournamentList tournaments) {
        super(
                "tournament-list",
                "",
                "Lists the ongoing tournaments" +
                        (role.isRole(Role.PLAYER) ?
                                "where you are participating." :
                                ".")
        );

        this.role = role;
        this.tournaments = tournaments;
    }

    public void run(String[] args) {
        if (role.isRole(Role.PLAYER)) {
            // TODO Print those tournaments in the list related to the player
        } else {
            // TODO Print all of the tournaments in the list
        }
    }
}
