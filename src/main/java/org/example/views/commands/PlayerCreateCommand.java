package org.example.views.commands;

import org.example.models.Database;
import org.example.Error;

import java.util.Deque;

public class PlayerCreateCommand extends Command {
    public PlayerCreateCommand(String name, String usage, String description) {
        super(name, usage, description);
    }

    public Error execute(String[] args) {
        Database database = new Database("players.csv");
        Deque<String> data = database.loadData();
        boolean flag = false;
        while (!flag && !data.isEmpty()) {
            String[] player = data.pop().split(",");
            flag = player[3].equals(args[0]);
        }
        if (!flag) {
            database.storeData(String.format(",%s,%s,%s,0|0|0|0|0", args[1], args[2], args[0]));
            return Error.NONE;
        } else {
            return Error.EXISTENT_PLAYER;
        }
    }
}
