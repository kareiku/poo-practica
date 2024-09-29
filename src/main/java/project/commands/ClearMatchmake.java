package project.commands;

import project.Match;

import java.util.LinkedList;

public class ClearMatchmake {
    public static void clearMatchmake(LinkedList<Match> matches) {
        matches = new LinkedList<>();
    }
}
