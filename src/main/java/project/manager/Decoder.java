package project.manager;

import project.utils.MatchList;
import project.utils.PlayerList;

import java.util.Scanner;

public class Decoder {
    Decoder() {
    }

    public String read() {
        String args;
        input(args = new Scanner(System.in).nextLine());
        return args;
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
                PlayerList.PLAYERS.create(args);
                break;
            case "remove":
                PlayerList.PLAYERS.remove(args);
                break;
            case "show":
                PlayerList.PLAYERS.show();
                break;
            case "rank":
                PlayerList.PLAYERS.rank();
                break;
            case "score":
                PlayerList.PLAYERS.score(args);
                break;
            case "show_matchmake":
                MatchList.MATCHES.show();
                break;
            case "clear_matchmake":
                MatchList.MATCHES.clear();
                break;
            case "matchmake":
                MatchList.MATCHES.create(args);
                break;
            case "random_matchmake":
                MatchList.MATCHES.randomizeAll();
                break;
        }
    }
}
