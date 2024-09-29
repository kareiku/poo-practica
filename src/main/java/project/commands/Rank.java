package project.commands;

import project.Player;

import java.util.LinkedList;

public class Rank {
    public static void rank(LinkedList<Player> players) {
        LinkedList<Player> temp = sorted(players);
        Show.show(temp);
    }

    private static LinkedList<Player> sorted(LinkedList<Player> players) {
        LinkedList<Player> aux = new LinkedList<>(players);

        int index = 0;
        int size = aux.size();

        while (index < size) {
            if (index == 0 || aux.get(index).getScore() <= aux.get(index - 1).getScore()) {
                index++;
            } else {
                Player temp = aux.get(index);
                aux.set(index, aux.get(index - 1));
                aux.set(index - 1, temp);
                index--;
            }
        }

        return aux;
    }
}
