package project.commands;

import project.Player;

import java.util.LinkedList;

public class ShowCommand {
    public static void show(LinkedList<Player> players) {
        System.out.println("Player\tScore");
        for (Player player : players) {
            System.out.println(player);
        }
    }
}
