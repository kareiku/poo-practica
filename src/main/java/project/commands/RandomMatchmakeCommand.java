package project.commands;

import project.Player;
import project.Match;

import java.util.LinkedList;

public class RandomMatchmakeCommand {
    public static void randomMatchmake(LinkedList<Player> players, LinkedList<Match> matches) {
        assert !players.isEmpty();
        assert players.size() % 2 != 0;

        do {
            Player home = getRandomPlayer(players);
            Player guest = getRandomPlayer(players);

            MatchmakeCommand.matchmake(players, matches, home.getName(), guest.getName());
        } while (!players.isEmpty());

    }

    private static Player getRandomPlayer(LinkedList<Player> players) {
        return players.get((int) (Math.random() * players.size()));
    }
}
