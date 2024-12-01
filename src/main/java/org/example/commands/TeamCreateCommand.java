package org.example.commands;

import org.example.views.CommandView;

public class TeamCreateCommand extends CommandView {
    public void execute(String[] args) {
        // TODO
    }

    public String name() {
        return "team-create";
    }

    public String usage() {
        return "<name>";
    }

    public String help() {
        return "Adds a team to the database.";
    }

    protected PrivilegeLevel privilegeLevel() {
        return /* Fixme. Again and again... */ null;
    }

    public boolean hasPermission() {
        return this.privilegeLevel() == PrivilegeLevel.ADMIN;
    }
}
