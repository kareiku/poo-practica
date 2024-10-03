package project;

import project.commands.*;

import java.util.Scanner;

public class CommandDecoder {
    public static String read(LinkedList<Player> players, LinkedList<Match> matches) {
        String ret;
        input(players, matches, ret = new Scanner(System.in).nextLine());
        return ret;
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
                RemoveCommand.remove(players, args[1]);
                break;
            case "show":
                ShowCommand.show(players);
                break;
            case "rank":
                RankCommand.rank(players);
                break;
            case "score":
                ScoreCommand.score(players, args[1], Double.parseDouble(args[2].replace(',', '.')));
                break;
            case "show_matchmake":
                ShowMatchmakeCommand.showMatchmake(matches);
                break;
            case "clear_matchmake":
                ClearMatchmakeCommand.clearMatchmake(matches);
                break;
            case "matchmake":
                MatchmakeCommand.matchmake(players, matches, args[1], args[2]);
                break;
            case "random_matchmake":
                RandomMatchmakeCommand.randomMatchmake(players, matches);
                break;
        }
    }
}
