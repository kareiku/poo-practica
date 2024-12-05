package org.example.views.commands;

import org.example.controllers.Controller;

public class TeamDeleteCommand extends Command {
    public TeamDeleteCommand(Controller controller) {
        super(controller);
    }

    protected Error executeTemplate(String[] args) {
    }
}
