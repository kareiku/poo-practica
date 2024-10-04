package project.commands;

import project.Player;
import project.LinkedList;
import project.Iterator;

public class CreateCommand {
    public static void create(LinkedList<Player> players, String name) {
        assert name != null;

        Iterator<Player> iterator = players.getIterator();
        Player temp;

        do {
            temp = iterator.next();
        } while (!name.equals(temp.getName()) && iterator.hasNext());

        if (!name.equals(temp.getName())) {
            players.add(new Player(name));
        }
    }
}