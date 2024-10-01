package project.commands;

import project.Match;

import java.util.LinkedList;

public class ShowMatchmakeCommand {
    public static void showMatchmake(LinkedList<Match> matches) {
        for (Match match : matches) {
            showMatchmake(match);
        }
    }

    private static void showMatchmake(Match match) {
    }
}
