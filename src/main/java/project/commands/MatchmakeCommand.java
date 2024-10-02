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

        if (!hasMatch(matches, homeAux) && !hasMatch(matches, guestAux)) {
            matches.add(new Match(homeAux, guestAux));
        }
    }

    private static boolean hasMatch(LinkedList<Match> matches, Player player) {
        boolean ret = false;

        int i = 0;
        while (!ret && i < matches.size()) {
            Match match = matches.get(i);
            if (match.getHome().equals(player) || match.getGuest().equals(player)) {
                ret = true;
            }
            i++;
        }
        return ret;
    }
}
