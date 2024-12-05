package org.example.views.commands;

import org.example.utils.Console;
import org.example.utils.Error;
import org.example.services.Controller;
import org.example.models.Role;

public abstract class Command {
    private final Controller controller;
    private final int requiredArgumentCount;
    private final Error permissionError;
    private final Role[] roles;

    protected Command(Controller controller, int requiredArgumentCount, Error permissionError, Role... roles) {
        this.controller = controller;
        this.requiredArgumentCount = requiredArgumentCount;
        this.permissionError = permissionError;
        this.roles = roles;
    }

    protected Controller getController() {
        return this.controller;
    }

    public void execute(String[] args) {
        if (this.getController().hasPermission(roles)) {
            if (args.length >= requiredArgumentCount) {
                Error error = this.executeTemplate(args);
                if (error != Error.NONE) {
                    Console.getInstance().println(error.getMessage());
                }
            } else {
                Console.getInstance().println(Error.INCORRECT_ARGUMENT_COUNT.getMessage());
            }
        } else {
            Console.getInstance().println(permissionError.getMessage());
        }
    }

    protected abstract Error executeTemplate(String[] args);
}
