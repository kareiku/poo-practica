package project;

import java.util.LinkedList;

public class Manager {
    private final LinkedList<Player> players;
    private final LinkedList<Match> matches;

    public Manager() {
        players = new LinkedList<>();
        matches = new LinkedList<>();
    }

    public String read() {
        return CommandDecoder.read(players, matches);
    }

    public void input(String command) {
        CommandDecoder.input(players, matches, command);
    }
}
