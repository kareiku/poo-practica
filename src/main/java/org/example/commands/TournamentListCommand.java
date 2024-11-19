package org.example.commands;

import org.example.models.Command;
import org.example.models.Role;
import org.example.models.TournamentList;

public class TournamentListCommand extends Command {
    private Role role;
    private final TournamentList tournaments;

    public TournamentListCommand(Role role, TournamentList tournaments) {
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
