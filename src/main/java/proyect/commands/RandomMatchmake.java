package proyect.commands;

import proyect.Player;
import proyect.Match;

import java.util.LinkedList;

public class RandomMatchmake {
    public static void randomMatchmake(LinkedList<Player> players, LinkedList<Match> matches) {
        Player home = randomPlayer(players);
        Player guest = randomPlayer(players);
        if (home != null && guest != null) {
            Matchmake.matchmake(matches, home, guest);
        }
    }

    private static Player randomPlayer(LinkedList<Player> players) {
        return players.isEmpty() ? null : players.get((int) (Math.random() * players.size()));
    }
}
