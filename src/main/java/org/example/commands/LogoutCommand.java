package org.example.commands;

import org.example.models.Command;

public class LogoutCommand extends Command {
    public LogoutCommand() {
        super(
                "logout",
                "",
                "Logs out the logged user from the system."
        );
    }

    public void run(String[] args) {
        // TODO
    }
}
