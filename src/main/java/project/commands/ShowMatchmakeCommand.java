package project.commands;

import project.Match;
import project.LinkedList;
import project.Iterator;

public class ShowMatchmakeCommand {
    public static void showMatchmake(LinkedList<Match> matches) {
        System.out.println("Current matches:");

        Iterator<Match> iterator = matches.getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
