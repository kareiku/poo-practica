package project.commands;

import project.Player;

import project.LinkedList;

public class CreateCommand {
    public static void create(LinkedList<Player> players, String name) {
        assert name != null;

        boolean isListed = false;
        int i = 0;

        while (!isListed && i < players.size()) {
            if (players.get(i).getName().equals(name)) {
                isListed = true;
            }
            i++;
        }

        if (!isListed) {
            players.add(new Player(name));
        }
    }
}
