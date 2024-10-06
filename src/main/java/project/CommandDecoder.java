package project;

import project.commands.*;

import java.util.Scanner;

public class CommandDecoder {
    public static String read(LinkedList<Player> players, LinkedList<Match> matches) {
        String args;
        input(players, matches, args = new Scanner(System.in).nextLine());
        return args;
    }

    public static void input(LinkedList<Player> players, LinkedList<Match> matches, String command) {
        assert command != null;
        decode(players, matches, command);
    }

    private static void decode(LinkedList<Player> players, LinkedList<Match> matches, String command) {
        String[] args = command.split("\\s+");
        assert args.length > 0;

        switch (args[0].toLowerCase()) {
            case "create":
                assert args.length > 1;
                CreateCommand.create(players, args[1]);
                break;
            case "remove":
                assert args.length > 1;
                RemoveCommand.remove(players, args[1]);
                break;
            case "show":
                ShowCommand.show(players);
                break;
            case "rank":
                RankCommand.rank(players);
                break;
            case "score":
                assert args.length > 1;
                ScoreCommand.score(players, args[1], Double.parseDouble(args[2].replace(',', '.')));
                break;
            case "show_matchmake":
                ShowMatchmakeCommand.showMatchmake(matches);
                break;
            case "clear_matchmake":
                ClearMatchmakeCommand.clearMatchmake(matches);
                break;
            case "matchmake":
                assert args.length > 1;
                MatchmakeCommand.matchmake(players, matches, args[1], args[2]);
                break;
            case "random_matchmake":
                RandomMatchmakeCommand.randomMatchmake(players, matches);
                break;
        }
    }
}
