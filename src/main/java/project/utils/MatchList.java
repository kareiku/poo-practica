package project.utils;

import project.manager.Iterator;
import project.manager.LinkedList;
import project.manager.Match;
import project.manager.Player;

public enum MatchList {
    MATCHES;

    private final LinkedList<Match> matches;

    MatchList() {
        this.matches = new LinkedList<>();
    }

    public void show() {
        System.out.println("Current matches:");

        Iterator<Match> iterator = matches.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void clear() {
        this.matches.clear();
    }

    public void create(String[] args) {
        assert args.length > 2;

        Player home = PlayerList.PLAYERS.get(args[1]);
        Player guest = PlayerList.PLAYERS.get(args[2]);

        this.create(home, guest);
    }

    private void create(Player home, Player guest) {
        assert home != guest; // checks if they're the same player by looking up their references

        if (this.matchmakeable(home) && this.matchmakeable(guest)) {
            this.matches.add(new Match(home, guest));
        }
    }

    public void randomizeAll() {
        LinkedList<Player> matchmakeables = new LinkedList<>();
        Iterator<Player> iterator = PlayerList.PLAYERS.listIterator();

        while (iterator.hasNext()) {
            Player player = iterator.next();
            if (this.matchmakeable(player) && !matchmakeables.contains(player)) {
                matchmakeables.add(player);
            }
        }

        assert matchmakeables.size() % 2 == 0;

        while (!matchmakeables.isEmpty()) {
            Player home = this.popRandom(matchmakeables);
            Player guest = this.popRandom(matchmakeables);
            this.create(home, guest);
        }
    }

    private Player popRandom(LinkedList<Player> list) {
        Player player = list.get((int) (Math.random() * list.size()));
        list.remove(player);
        return player;
    }

    public boolean matchmakeable(Player player) {
        boolean matched = false;
        Iterator<Match> iterator = this.matches.listIterator();

        while (!matched && iterator.hasNext()) {
            Match match = iterator.next();
            matched = match.isComposedBy(player);
        }

        return !matched;
    }
}
