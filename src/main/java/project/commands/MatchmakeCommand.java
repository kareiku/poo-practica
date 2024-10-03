package project.commands;

import project.Player;
import project.Match;
import project.LinkedList;
import project.Iterator;

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
        boolean has = false;

        int i = 0;
        while (!has && i < matches.size()) {
            Match match = matches.get(i);
            if (match.getHome().equals(player) || match.getGuest().equals(player)) {
                has = true;
            }
            i++;
        }
        return has;
    }
}
