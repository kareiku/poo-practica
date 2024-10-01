package project.commands;

import project.Player;

import java.util.LinkedList;

public class CreateCommand {
    public static void create(LinkedList<Player> players, Player player) {
        assert player != null;

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
