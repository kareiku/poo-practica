package org.example.views.commands;

import org.example.models.Player;
import org.example.models.Role;
import org.example.views.Command;

import java.util.List;

public class PlayerDeleteCommand extends Command {
    private final List<Player> players;

    public PlayerDeleteCommand(List<Player> players) {
        super("player-delete", "<DNI>", "Removes a player from the system.", Role.ADMIN);
        this.players = players;
    }

    public void execute(String[] args) {
        this.players.removeIf(player -> player.isPlayer(args[0]));
    }
}
