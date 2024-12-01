package org.example.commands;

import org.example.models.Player;

public class PlayerCreateCommand extends Command {
    public PlayerCreateCommand(String name, String usage, String description) {
        super(name, usage, description);
    }

    public void execute(String[] args) {
        participants.add(new Player(args[0], args[1], args[2], args[3], args[4]));
    }
}
