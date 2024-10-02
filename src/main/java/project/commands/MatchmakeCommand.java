package project.commands;

import project.Player;
import project.Match;

import java.util.LinkedList;

public class MatchmakeCommand {
    public static void matchmake(LinkedList<Player> players, LinkedList<Match> matches, String home, String guest) {
        Player homeAux = null;
        Player guestAux = null;

        for (Player player : players) {
            if (player.getName().equals(home)) {
                homeAux = player;
            } else if (player.getName().equals(guest)) {
                guestAux = player;
            }
        }

        matches.add(new Match(homeAux, guestAux));
    }
}
