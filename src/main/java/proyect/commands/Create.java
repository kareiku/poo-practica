package proyect.commands;

import proyect.Player;

import java.util.LinkedList;

public class Create {
    public static void create(LinkedList<Player> players, Player player) {
        boolean isListed = false;
        int i = 0;

        while (!isListed && i < players.size()) {
            if (players.get(i).equals(player)) {
                isListed = true;
            }
            i++;
        }

        if (!isListed) {
            players.offer(player);
        }
    }
}
