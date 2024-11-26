package org.example.views.commands;

import org.example.models.Player;
import org.example.views.Command;
import org.example.models.Role;

import java.util.List;

public final class PlayerCreateCommand extends Command {
    private final List<Player> players;

    public PlayerCreateCommand(List<Player> players) {
        super("player-create", "<DNI>;<name>;<surnames>", "Creates a player in the system.", Role.ADMIN);
        this.players = players;
    }

    public void execute(String[] args) {
        Player player = new Player(args[0], args[1], args[2], args[3], args[4]);
        if (!this.players.contains(player)) {
            players.add(player);
        }
    }
}
