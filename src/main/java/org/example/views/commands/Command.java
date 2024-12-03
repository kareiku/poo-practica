package org.example.views.commands;

import org.example.models.Error;

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

    public void displayHelp() {
        System.out.printf("%s\nUsage: %s %s\n%s\n\n",
                this.name,
                this.name,
                this.usage,
                this.description);
    }

    public abstract Error execute(String[] args);
}
