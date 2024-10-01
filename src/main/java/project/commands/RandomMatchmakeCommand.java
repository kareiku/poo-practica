package project.commands;

import project.Player;
import project.Match;

import java.util.LinkedList;

public class RandomMatchmakeCommand {
    public static void randomMatchmake(LinkedList<Player> players, LinkedList<Match> matches) {
        Player home = randomPlayer(players);
        Player guest = randomPlayer(players);
        assert home != null && guest != null;
        MatchmakeCommand.matchmake(matches, home, guest);
    }

    private static Player randomPlayer(LinkedList<Player> players) {
        return players.isEmpty() ? null : players.get((int) (Math.random() * players.size()));
    }
}
