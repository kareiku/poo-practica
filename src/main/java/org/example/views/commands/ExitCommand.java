package org.example.views.commands;

import org.example.controllers.Controller;
import org.example.utils.Error;

public class ExitCommand extends Command {
    public ExitCommand(Controller controller) {
        super(controller, 0, Error.NONE);
    }

    protected Error executeTemplate(String[] args) {
        return Error.NONE;
    }
}
