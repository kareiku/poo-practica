package org.example.views.commands;

import org.example.models.Role;
import org.example.views.Command;

public class LogoutCommand extends Command {
    public LogoutCommand() {
        super("logout", "", "Attempts to log off the current user from the system, if logged.", Role.GUEST);
    }

    public void execute(String[] args) {
        // TODO: Set the User to new User or null or something.
    }
}
