package org.example.views;

import org.example.controllers.Controller;
import org.example.utils.Role;
import org.example.utils.Console;
import org.example.utils.Error;

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
        if (this.getController().hasPermission(this.roles)) {
            if (args.length >= this.requiredArgumentCount) {
                Error error = this.executionTemplate(args);
                if (error.isNotNone()) {
                    Console.getInstance().println(error.getMessage());
                }
            } else {
                Console.getInstance().println(Error.INCORRECT_ARGUMENT_COUNT.getMessage());
            }
        } else {
            if (this.permissionError.isNotNone()) {
                Console.getInstance().println(this.permissionError.getMessage());
            }
        }
    }

    protected abstract Error executionTemplate(String[] args);
}
