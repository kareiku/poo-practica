package org.example.commands;

import org.example.models.Command;

public class TournamentMatchmakingCommand extends Command {
    public void execute(String[] args) {
        // TODO
    }

    public String name() {
        return "tournament-matchmaking";
    }

    public String usage() {
        return "" /* TODO */;
    }

    public String help() {
        return "" /* TODO */;
    }

    protected PrivilegeLevel privilegeLevel() {
        return /* Fixme. Again and again... */ null;
    }

    public boolean hasPermission() {
        return this.privilegeLevel() == PrivilegeLevel.ADMIN;
    }
}
