package org.example.views.commands;

import org.example.views.Command;

public class TournamentListCommand extends Command {
    public void execute(String[] args) {
        if (this.privilegeLevel() == PrivilegeLevel.PLAYER) {
            // TODO Print those tournaments in the list related to the player.
        } else {
            // TODO Print all of the tournaments in the list.
        }
    }

    public String name() {
        return "tournament-list";
    }

    public String usage() {
        return "";
    }

    public String help() {
        return "List the ongoing tournaments. Reduced format to the ones you're participant, if such user is logged.";
    }

    protected PrivilegeLevel privilegeLevel() {
        return /* Fixme. Again and again... */ null;
    }

    public boolean hasPermission() {
        return this.privilegeLevel().ordinal() <= PrivilegeLevel.GUEST.ordinal();
    }
}
