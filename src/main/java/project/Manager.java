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
        assert player != null;
        CreateCommand.create(players, player);
    }

    public void remove(Player player) {
        assert player != null;
        RemoveCommand.remove(players, player);
    }

    public void show() {
        ShowCommand.show(players);
    }

    public void rank() {
        RankCommand.rank(players);
    }

    public void score(Player player, double score) {
        assert player != null;
        ScoreCommand.score(player, score);
    }

    public void showMatchmake() {
        ShowMatchmakeCommand.showMatchmake(matches);
    }

    public void clearMatchmake() {
        ClearMatchmakeCommand.clearMatchmake(matches);
    }

    public void matchmake(Player home, Player guest) {
        assert home != null && guest != null;
        MatchmakeCommand.matchmake(matches, home, guest);
    }

    public void randomMatchmake() {
        RandomMatchmakeCommand.randomMatchmake(players, matches);
    }
}
