package org.example.commands;

import org.example.models.Command;

public class StatisticsShowCommand extends Command {
    public void execute(String[] args) {
        // TODO
    }

    public String name() {
        return "statistics-show";
    }

    public String usage() {
        return "";
    }

    public String help() {
        return "Shows the statistics of the logged player or their team, in their case.";
    }

    protected PrivilegeLevel privilegeLevel() {
        return /* Fixme. Third case with the problem of getting the privilege from the current user. */ null;
    }

    public boolean hasPermission() {
        return this.privilegeLevel().ordinal() <= PrivilegeLevel.GUEST.ordinal();
    }
}
