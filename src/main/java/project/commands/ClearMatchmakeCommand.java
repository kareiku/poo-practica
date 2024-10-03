package project.commands;

import project.Match;

import project.LinkedList;

public class ClearMatchmakeCommand {
    public static void clearMatchmake(LinkedList<Match> matches) {
        assert matches != null;

        matches.clear(); // matches = new LinkedList<Match>();
    }
}
