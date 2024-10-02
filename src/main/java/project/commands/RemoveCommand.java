package project.commands;

import project.Player;

import java.util.LinkedList;

public class RemoveCommand {
    public static void remove(LinkedList<Player> players, String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                players.remove(player);
            }
        }
    }
}
