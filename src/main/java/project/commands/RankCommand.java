package project.commands;

import project.Player;

import java.util.LinkedList;

public class RankCommand {
    public static void rank(LinkedList<Player> players) {
        LinkedList<Player> temp = sorted(players);
        ShowCommand.show(temp);
    }

    private static LinkedList<Player> sorted(LinkedList<Player> players) {
        LinkedList<Player> aux = new LinkedList<>(players);
        sort(aux);
        return aux;
    }

    private static void sort(LinkedList<Player> list) {
        int index = 0;
        int size = list.size();

        while (index < size) {
            if (index == 0 || list.get(index).getScore() <= list.get(index - 1).getScore()) {
                index++;
            } else {
                Player temp = list.get(index);
                list.set(index, list.get(index - 1));
                list.set(index - 1, temp);
                index--;
            }
        }
    }
}
