package project.commands;

import project.Player;
import project.Match;
import project.LinkedList;
import project.Iterator;

public class MatchmakeCommand {
    public static void matchmake(LinkedList<Player> players, LinkedList<Match> matches, String home, String guest) {
        Player homeAux = null;
        Player guestAux = null;
        Iterator<Player> iterator = players.getIterator();
        Player temp;

        while (iterator.hasNext()) {
            temp = iterator.next();
            if (temp.getName().equals(home)) {
                homeAux = temp;
            } else if (temp.getName().equals(guest)) {
                guestAux = temp;
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
