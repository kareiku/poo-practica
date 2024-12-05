package org.example.views.commands;

import org.example.controllers.Controller;
import org.example.models.Role;
import org.example.utils.Console;
import org.example.utils.Error;

public class StatisticsShowCommand extends Command {
    public StatisticsShowCommand(Controller controller) {
        super(controller, 0, Error.NO_PERMISSION, Role.PLAYER);
    }

    protected Error executeTemplate(String[] args) {
        String option = !args[0].isEmpty() ? args[0] : null;
        Console.getInstance().println(this.getController().showStats(option));
        return Error.NONE;
    }
}
