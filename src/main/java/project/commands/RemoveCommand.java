package project.commands;

import project.Player;
import project.LinkedList;
import project.Iterator;

public class RemoveCommand {
    public static void remove(LinkedList<Player> players, String name) {
        Iterator<Player> iterator = players.getIterator();
        Player temp;

        do {
            temp = iterator.next();
            if (temp.getName().equals(name)) {
                players.remove(temp);
            }
        } while (!temp.getName().equals(name) && iterator.hasNext());
    }
}
