package org.example.commands;

import org.example.views.CommandView;

public class TeamRemoveCommand extends CommandView {
    public void execute(String[] args) {
        // TODO
    }

    public String name() {
        return "team-remove";
    }

    public String usage() {
        return "<DNI>";
    }

    public String help() {
        return "Removes the specified player from the specified team.";
    }

    protected PrivilegeLevel privilegeLevel() {
        return /* Fixme. Again and again... */ null;
    }

    public boolean hasPermission() {
        return this.privilegeLevel() == PrivilegeLevel.ADMIN /* Fixme. I think this was the correct privilege. */;
    }
}
