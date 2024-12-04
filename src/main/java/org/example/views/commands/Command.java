package org.example.views.commands;

import org.example.Console;
import org.example.Error;
import org.example.controllers.Controller;
import org.example.models.Role;

public abstract class Command {
    private final Controller controller;

    protected Command(Controller controller) {
        this.controller = controller;
    }

    protected Controller getController() {
        return this.controller;
    }

    public void execute(String[] args) {
        if (this.getController().hasPermission(this.templateRoles())) {
            if (args.length >= this.templateArgumentCount()) {
                Error error = this.templateMethod(args);
                if (error != Error.NONE) {
                    Console.getInstance().println(error.getMessage());
                }
            } else {
                Console.getInstance().println(org.example.Error.INCORRECT_ARGUMENT_COUNT.getMessage());
            }
        } else {
            Console.getInstance().println(this.templatePermissionError().getMessage()); // fixme Set correct error.
        }
    }

    protected abstract Role[] templateRoles();

    protected abstract int templateArgumentCount();

    protected abstract Error templatePermissionError();

    protected abstract Error templateMethod(String[] args);
}
