package org.example.commands;

public class ExitCommand extends Command {
    private boolean executed;

    public ExitCommand(String name, String usage, String description) {
        super(name, usage, description);
        this.executed = false;
    }

    public void execute(String[] args) {
        this.executed = true;
    }

    public boolean hasBeenExecuted() {
        return this.executed;
    }
}
