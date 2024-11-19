package org.example.models;

public abstract class Command {
    public abstract void execute(String[] args);

    public final boolean matches(String commandName) {
        return this.name().equals(commandName);
    }

    public abstract String name();

    public abstract String usage();

    public abstract String help();

    // Fixme. In fact, shouldn't Command be the one extracting the privilege from the user directly?
    //  Like, defining this in here instead of doing abstract?
    protected abstract PrivilegeLevel privilegeLevel();

    public final boolean hasPermission(User user) {
        return user.privilegeLevel().ordinal() <= this.privilegeLevel().ordinal();
    }
}
