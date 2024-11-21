package org.example.views.commands;

import org.example.views.Command;

public class TournamentDeleteCommand extends Command {
    public void execute(String[] args) {
        // TODO
    }

    public String name() {
        return "tournament-delete";
    }

    public String usage() {
        return "<name>";
    }

    public String help() {
        return "Deletes the specified tournament.";
    }

    protected PrivilegeLevel privilegeLevel() {
        return /* Fixme. Again and again... */ null;
    }

    public boolean hasPermission() {
        return this.privilegeLevel() == PrivilegeLevel.ADMIN;
    }
}
