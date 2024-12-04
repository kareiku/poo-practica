package org.example.views.commands;

import org.example.Console;
import org.example.Error;
import org.example.controllers.Controller;

public class PlayerCreateCommand extends Command {
    public PlayerCreateCommand(Controller controller) {
        super(controller);
    }

    public void execute(String[] args) {
        if (args.length >= 3) {
            if (this.isDNIValid(args[2])) {
                Error error = this.controller().addPlayer(args[0], args[1], args[2]);
                if (error != Error.NONE) {
                    Console.getInstance().println(error.getMessage());
                }
            }
        }
    }

    private boolean isDNIValid(String DNI) {
        return DNI.matches("^\\d{8}[A-Za-z]$");
    }
}
