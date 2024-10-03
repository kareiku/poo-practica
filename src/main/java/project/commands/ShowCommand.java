package project.commands;

import project.Player;
import project.LinkedList;
import project.Iterator;

public class ShowCommand {
    public static void show(LinkedList<Player> players) {
        System.out.println(String.format("%-25s %s", "Players", "Score"));

        for (Player player : players) {
            System.out.println(player);
        }
    }
}
