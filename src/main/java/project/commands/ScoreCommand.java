package project.commands;

import project.Player;
import project.LinkedList;
import project.Iterator;

public class ScoreCommand {
    public static void score(LinkedList<Player> players, String name, double score) {
        Iterator<Player> iterator = players.getIterator();
        Player tempPlayer;
        while (iterator.hasnext()) {
            tempPlayer = iterator.next();
            if (tempPlayer.getName().equals(name)) {
                tempPlayer.setScore(score);
            }
        }
        /*
        for (Player player : players) {
            if (player.getName().equals(name)) {
                player.setScore(score);
            }
        }
        */
    }
}
