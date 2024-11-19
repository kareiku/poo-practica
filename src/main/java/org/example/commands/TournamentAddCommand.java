package org.example.commands;

import org.example.models.Command;

public class TournamentAddCommand extends Command {
    public void execute(String[] args) {
        // TODO
    }

    public String name() {
        return "tournament-add";
    }

    public String usage() {
        return "<DNI | team name>;<tournament name>";
    }

    public String help() {
        return "Adds a player or a team to a tournament.";
    }

    protected PrivilegeLevel privilegeLevel() {
        return /* Fixme. Again and again... */ null;
    }

    public boolean hasPermission() {
        return this.privilegeLevel() == PrivilegeLevel.ADMIN;
    }
}
