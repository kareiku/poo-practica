package org.example.views.commands;

import org.example.controllers.Controller;

public class TeamCreateCommand extends Command {
    public TeamCreateCommand(Controller controller) {
        super(controller);
    }

    protected Error executeTemplate(String[] args) {
    }
}
