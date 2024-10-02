package project.commands;

import project.Player;
import project.Match;

import java.util.LinkedList;

public class RandomMatchmakeCommand {
    public static void randomMatchmake(LinkedList<Player> players, LinkedList<Match> matches) {
        assert !players.isEmpty();
        assert players.size() % 2 != 0;

        LinkedList<Player> temp = new LinkedList<>(players);
        while (players.size() >= 2) {
            Player home = getRandomPlayer(temp);
            temp.remove(home);
            Player guest = getRandomPlayer(temp);
            temp.remove(guest);

            MatchmakeCommand.matchmake(players, matches, home.getName(), guest.getName());
        }

    }

    private static Player getRandomPlayer(LinkedList<Player> players) {
        return players.get((int) (Math.random() * players.size()));
    }
}
