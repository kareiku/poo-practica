package project.commands;

import project.Player;

import java.util.LinkedList;

public class ShowCommand {
    public static void show(LinkedList<Player> players) {
        System.out.println(String.format("%-25s %s", "Players", "Score"));
        for (Player player : players) {
            System.out.println(player);
        }
    }
}
