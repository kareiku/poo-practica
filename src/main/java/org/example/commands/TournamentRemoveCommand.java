package org.example.commands;

import org.example.models.Command;

public class TournamentRemoveCommand extends Command {
    public void execute(String[] args) {
        // TODO
    }

    public String name() {
        return "tournament-remove";
    }

    public String usage() {
        return "<tournament name>";
    }

    public String help() {
        return "Deletes a tournament, regardless of its current state.";
    }

    protected PrivilegeLevel privilegeLevel() {
        return /* Fixme. Again and again... */ null;
    }

    public boolean hasPermission() {
        return this.privilegeLevel() == PrivilegeLevel.ADMIN;
    }
}
