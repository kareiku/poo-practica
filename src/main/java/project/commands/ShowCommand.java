package project.commands;

import project.Player;
import project.LinkedList;
import project.Iterator;

public class ShowCommand {
    public static void show(LinkedList<Player> players) {
        System.out.printf("%-25s %s\n", "Players", "Score");
        Iterator<Player> iterator = players.getIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
