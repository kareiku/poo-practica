package project.commands;

import project.Match;

import project.LinkedList;

public class ShowMatchmakeCommand {
    public static void showMatchmake(LinkedList<Match> matches) {
        System.out.println("Current matches:");
        for (Match match : matches) {
            System.out.println(match);
        }
    }
}
