package org.example.commands;

public abstract class Command {
    private final String name;
    private final String usage;
    private final String description;

    Command(String name, String usage, String description) {
        this.name = name;
        this.usage = usage;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }

    protected abstract void execute(String[] args);
}
