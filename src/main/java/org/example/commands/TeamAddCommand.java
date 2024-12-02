package org.example.commands;

public class TeamAddCommand extends Command {
    public TeamAddCommand(String name, String usage, String description) {
        super(name, usage, description);
    }

    public void execute(String[] args) {
        this.participants.add();
    }
}
