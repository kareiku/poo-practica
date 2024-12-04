package org.example.views.commands;

import org.example.controllers.Controller;

public abstract class Command {
    private final Controller controller;

    protected Command(Controller controller) {
        this.controller = controller;
    }

    protected Controller controller() {
        return controller;
    }

    public abstract void execute(String[] args);
}
