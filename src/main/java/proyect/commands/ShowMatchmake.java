package proyect.commands;

import proyect.Match;

import java.util.LinkedList;

public class ShowMatchmake {
    public static void showMatchmake(LinkedList<Match> matches) {
        for (Match match : matches) {
            showMatchmake(match);
        }
    }

    private static void showMatchmake(Match match) {
    }
}
