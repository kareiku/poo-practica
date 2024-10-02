package project.commands;

import project.Player;

import java.util.LinkedList;

public class ScoreCommand {
    public static void score(LinkedList<Player> players, String name, double score) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                player.setScore(score);
            }
        }
    }
}
