package project.commands;

import project.Player;

public class ScoreCommand {
    public static void score(Player player, double score) {
        player.setScore(score);
    }
}