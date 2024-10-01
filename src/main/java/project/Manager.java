package project;

import project.commands.*;

import java.util.LinkedList;
import java.util.Scanner;

public class Manager {
    private final LinkedList<Player> players;
    private final LinkedList<Match> matches;

    public Manager() {
        players = new LinkedList<>();
        matches = new LinkedList<>();
    }

    public void read() {
        Scanner sc = new Scanner(System.in);

        String command = sc.next();

        assert command != null;

        this.run(command);
    }

    private void run(String command) {
        assert !command.startsWith("run ");

        // todo select command

        
    }
}
