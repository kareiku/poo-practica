package org.example.views.commands;

import org.example.Error;

public class LoginCommand extends Command {
    public LoginCommand(String name, String usage, String description) {
        super(name, usage, description);
    }

    public Error execute(String[] args) {
        return Error.NONE;
    }
}
