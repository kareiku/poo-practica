package org.example.views.commands;

import org.example.models.Role;
import org.example.views.Command;

public class TournamentListCommand extends Command {
    public TournamentListCommand() {
        super("tournament-list", "", "List the ongoing tournaments. Reduced format to the ones you're participant, if such user is logged.", Role.GUEST);
    }

    public void execute(String[] args) {
        if (this.privilegeLevel() == PrivilegeLevel.PLAYER) {
            // TODO Print those tournaments in the list related to the player.
        } else {
            // TODO Print all of the tournaments in the list.
        }
    }
}
