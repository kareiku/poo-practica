package proyect.commands;

import proyect.Player;

import java.util.LinkedList;

public class Show {
    public static void show(LinkedList<Player> players) {
        System.out.println("Player\tScore");
        for (Player player : players) {
            System.out.println(player);
        }
    }
}
