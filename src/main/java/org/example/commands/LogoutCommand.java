package org.example.commands;

import org.example.models.Role;
import org.example.views.CommandView;

public class LogoutCommand extends CommandView {
    public LogoutCommand() {
        super("logout", "", "Attempts to log off the current user from the system, if logged.", Role.GUEST);
    }

    public void execute(String[] args) {
        // TODO: Set the User to new User or null or something.
    }
}
