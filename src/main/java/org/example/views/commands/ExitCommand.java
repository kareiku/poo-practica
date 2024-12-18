package org.example.views.commands;

import org.example.controllers.Controller;
import org.example.utils.Error;

public class ExitCommand extends Command implements CommandToExit {
    private boolean executed;

    public ExitCommand(Controller controller) {
        super(controller, 0, Error.NONE);
        this.executed = false;
    }

    protected Error executionTemplate(String[] args) {
        this.executed = true;
        return Error.NONE;
    }

    public boolean hasBeenExecuted() {
        return this.executed;
    }
}
