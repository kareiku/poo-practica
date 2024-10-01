package project.commands;

import project.Player;

import java.util.LinkedList;

public class RemoveCommand {
    public static void remove(LinkedList<Player> players, Player player) {
        for (Player element : players) {
            if (element.equals(player)) {
                players.remove(player);
            }
        }
    }
}
