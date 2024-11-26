package org.example.views;

import org.example.models.Role;

public abstract class Command {
    private final String name;
    private final String usage;
    private final String description;
    private final Role role;

    public Command(String name, String usage, String help, Role role) {
        this.name = name;
        this.usage = usage;
        this.description = help;
        this.role = role;
    }

    public final String getName() {
        return this.name;
    }

    public final void showHelp() {
        System.out.printf("%s\nUsage: %s %s\n%s\n\n",
                this.name,
                this.name,
                this.usage,
                this.description);
    }

    public abstract void execute(String[] args);
}
