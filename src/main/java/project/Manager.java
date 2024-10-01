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
        input(new Scanner(System.in).nextLine());
    }

    public void input(String command) {
        assert command != null;
        decode(command);
    }

    private void decode(String command) {
        String[] args = command.split("\\s+");
        assert args.length > 0;

        switch (args[0].toLowerCase()) {
            case "create":
                assert args.length > 1;
                CreateCommand.create(players, new Player(args[1]));
                break;
            case "remove":
                RemoveCommand.remove(players, new Player(args[1]));
                break;
            case "show":
                ShowCommand.show(players);
                break;
            case "rank":
                RankCommand.rank(players);
                break;
            case "score":
                ScoreCommand.score(new Player(args[1]), Double.parseDouble(args[2].replace(',', '.')));
                break;
            case "show_matchmake":
                ShowMatchmakeCommand.showMatchmake(matches);
                break;
            case "clear_matchmake":
                ClearMatchmakeCommand.clearMatchmake(matches);
                break;
            case "matchmake":
                MatchmakeCommand.matchmake(matches, new Player(args[1]), new Player(args[2]));
                break;
            case "random_matchmake":
                RandomMatchmakeCommand.randomMatchmake(players, matches);
                break;
            default:
                System.err.println("Error: command not found.");
        }
    }
}
