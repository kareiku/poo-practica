package org.example.models;

public abstract class Command {
    private final String name;
    private final String usage;
    private final String description;

    public Command(String name, String usage, String description) {
        this.name = name;
        this.usage = usage;
        this.description = description;
    }

    public abstract void run(String[] args);

    public boolean matches(String commandName) {
        return commandName.matches(this.name);
    }

    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }
}
