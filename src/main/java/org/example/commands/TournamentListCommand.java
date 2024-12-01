package org.example.commands;

import org.example.models.Role;
import org.example.views.CommandView;

public class TournamentListCommand extends CommandView {
    public TournamentListCommand() {
        super("tournament-list", "", "List the ongoing tournaments. Reduced format to the ones you're participant, if such user is logged.", Role.GUEST);
    }

    // fixme wrong
    public void execute(String[] args) {
        if (this.privilegeLevel() == PrivilegeLevel.PLAYER) {
        } else {
        }
    }


    // TODO use Collections.shuffle() for the CASE non-logged
}
