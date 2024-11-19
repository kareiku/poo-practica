package org.example.commands;

import org.example.models.Command;

public class TeamAddCommand extends Command {
    public void execute(String[] args) {
        /* TODO
         *  - Search for player
         *  - Search for team
         *  - If both exist, add player to team
         */
    }

    public String name() {
        return "team-add";
    }

    public String usage() {
        return "<DNI>;<team name>";
    }

    public String help() {
        return "Adds the specified player to the specified team.";
    }

    protected PrivilegeLevel privilegeLevel() {
        return /* Fixme. Again and again... */ null;
    }

    public boolean hasPermission() {
        return this.privilegeLevel() == PrivilegeLevel.ADMIN;
    }
}
