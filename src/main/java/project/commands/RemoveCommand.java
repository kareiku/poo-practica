package project.commands;

import project.Player;

import java.util.LinkedList;

public class RemoveCommand {
    public static void remove(LinkedList<Player> players, Player player) {
        players.remove(player);
    }
}
