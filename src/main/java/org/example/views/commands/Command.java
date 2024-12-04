package org.example.views.commands;

import org.example.controllers.Controller;

public abstract class Command {
    protected Command(Controller controller) {
    }

    public abstract void execute(String... args);
}
