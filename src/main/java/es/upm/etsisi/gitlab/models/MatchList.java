package es.upm.etsisi.gitlab.models;

import java.util.LinkedList;
import java.util.Random;

public class MatchList extends LinkedList<Match> {
    public MatchList() {
    }

    @Deprecated
    public void create(String[] args) {
        assert args.length > 2;

        this.create(new Player[]{players.get(args[1]), players.get(args[2])});
    }

    @Deprecated
    private void create(Player[] players) {
        assert players[0] != players[1]; // Checks if they're the same player by looking up their references.

        try {
            if (this.matchmakeable(players[0]) && this.matchmakeable(players[1])) {
                this.add(new Match(players));
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @Deprecated
    public void randomizeAll() {
        LinkedList<Player> matchmakeables = new LinkedList<>();
        Iterator<Player> iterator = players.listIterator();

        while (iterator.hasNext()) {
            Player player = iterator.next();
            if (this.matchmakeable(player) && !matchmakeables.contains(player)) {
                matchmakeables.add(player);
            }
        }

        assert matchmakeables.size() % 2 == 0;

        while (!matchmakeables.isEmpty()) {
            this.create(new Player[]{this.popRandom(matchmakeables), this.popRandom(matchmakeables)});
        }
    }

    @Deprecated
    private Player popRandom(LinkedList<Player> list) {
        Player player = list.get((int) (new Random().nextInt() % list.size()));
        list.remove(player);
        return player;
    }

    @Deprecated
    public boolean matchmakeable(Player player) {
        boolean matched = false;
        Iterator<Match> iterator = this.listIterator();

        while (!matched && iterator.hasNext()) {
            Match match = iterator.next();
            matched = match.isComposedBy(player);
        }

        return !matched;
    }

    public void add(Participant[] participants) {
        this.add(new Match(participants));
    }

    public String toString() {
        StringBuilder format = new StringBuilder("Current matches:\n");
        for (Match match : this) {
            format.append(match).append("\n");
        }
        return format.toString();
    }
}
