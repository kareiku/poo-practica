package project;

import project.commands.*;

import java.util.LinkedList;

public class Manager {
    private final LinkedList<Player> players;
    private final LinkedList<Match> matches;

    public Manager() {
        players = new LinkedList<>();
        matches = new LinkedList<>();
    }

    public void create(Player player) {
        Create.create(players, player);
    }

    public void remove(Player player) {
        Remove.remove(players, player);
    }

    public void show() {
        Show.show(players);
    }

    public void rank() {
        Rank.rank(players);
    }

    public void score(Player player, double score) {
        Score.score(player, score);
    }

    public void showMatchmake() {
        ShowMatchmake.showMatchmake(matches);
    }

    public void clearMatchmake() {
        ClearMatchmake.clearMatchmake(matches);
    }

    public void matchmake(Player home, Player guest) {
        Matchmake.matchmake(matches, home, guest);
    }

    public void randomMatchmake() {
        RandomMatchmake.randomMatchmake(players, matches);
    }
}
