package project.commands;

import project.Player;
import project.LinkedList;
import project.Iterator;

public class ScoreCommand {
    public static void score(LinkedList<Player> players, String name, double score) {
        Iterator<Player> iterator = players.getIterator();
        Player temp;
        boolean flag = false;

        while (iterator.hasNext() && !flag) {
            temp = iterator.next();
            if (temp.getName().equals(name)) {
                temp.setScore(score);
                flag = true;
            }
        }
    }
}
