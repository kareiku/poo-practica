package proyect.commands;

import proyect.Player;

import java.util.LinkedList;

public class Rank {
    public static void rank(LinkedList<Player> players) {
        LinkedList<Player> temp = sorted(players);
        Show.show(temp);
    }

    private static LinkedList<Player> sorted(LinkedList<Player> players) {
        LinkedList<Player> temp = new LinkedList<>(players);
        LinkedList<Player> sorted = new LinkedList<>();

        Player aux;
        while(!temp.isEmpty()){
            aux = temp.peek();
        }

        return sorted;
    }
}
