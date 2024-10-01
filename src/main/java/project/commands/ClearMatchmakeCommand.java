package project.commands;

import project.Match;

import java.util.LinkedList;

public class ClearMatchmakeCommand {
    public static void clearMatchmake(LinkedList<Match> matches) {
        assert matches != null;

        matches.clear();
    }
}
