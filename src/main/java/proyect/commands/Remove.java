package proyect.commands;

import proyect.Player;

import java.util.LinkedList;

public class Remove {
    public static void remove(LinkedList<Player> players, Player player) {
        players.remove(player);
    }
}
