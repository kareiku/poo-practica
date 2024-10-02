package project.commands;

import project.Player;

import java.util.LinkedList;

public class RemoveCommand {
    // fixme
    public static void remove(LinkedList<Player> players, Player player) {
        for (Player element : players) {
            if (element.getName().equals(player.getName())) {
                players.remove(player);
            }
        }
    }
}
