package org.example.commands;

import org.example.models.Command;

public class TeamDeleteCommand extends Command {
    public void execute(String[] args) {
        // TODO
    }

    public String name() {
        return "team-delete";
    }

    public String usage() {
        return "<name>";
    }

    public String help() {
        return "Removes a team from the database.";
    }

    protected PrivilegeLevel privilegeLevel() {
        return /* Fixme. Again and again... */ null;
    }

    public boolean hasPermission() {
        return this.privilegeLevel() == PrivilegeLevel.ADMIN;
    }
}
