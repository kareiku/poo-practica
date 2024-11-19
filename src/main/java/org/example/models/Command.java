package org.example.models;

public abstract class Command {
    public abstract void execute(String[] args);

    public final boolean matches(String commandName) {
        return this.name().equals(commandName);
    }

    public abstract String name();

    public abstract String usage();

    public abstract String help();

    public abstract int privilegeLevel();
}
