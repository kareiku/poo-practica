
import project.Player;

import project.LinkedList;
import project.Iterator;

public class RemoveCommand {
    public static void remove(LinkedList<Player> players, String name) {
        Iterator<Player> iterator = players.getIterator();
        Player temp;
        while (iterator.hasNext()) {
            temp = iterator.next();
            if (temp.getName().equals(name)) {
                players.remove(temp);
            }
        }

        /*
        for (Player player : players) {
            if (player.getName().equals(name)) {
                players.remove(player);
            }
        }
         */
    }
}
