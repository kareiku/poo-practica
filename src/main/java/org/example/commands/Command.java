package org.example.commands;

import org.example.models.Error;
import org.example.models.Role;

public abstract class Command {
    private final String name;
    private final String usage;
    private final String description;
    private final Role[] roles;

    Command(String name, String usage, String description, Role[] roles) {
        this.name = name;
        this.usage = usage;
        this.description = description;
        this.roles = roles;
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

    public Role[] getRoles() {
        return roles;
    }

    protected abstract Error execute(String[] args);
}
