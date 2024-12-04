package org.example.views.commands;

import org.example.Console;
import org.example.Error;
import org.example.controllers.Controller;
import org.example.models.Role;

public class PlayerCreateCommand extends Command {
    public PlayerCreateCommand(Controller controller) {
        super(controller);
    }

    public void execute(String[] args) {
        if (this.getController().hasPermission(Role.ADMIN)) {
            if (args.length >= 3) {
                if (args[2].matches("^\\d{8}[A-Za-z]$")) {
                    Error error = this.getController().addPlayer(args[0], args[1], args[2]);
                    if (error != Error.NONE) {
                        Console.getInstance().println(error.getMessage());
                    }
                }
            } else {
                Console.getInstance().println(Error.INCORRECT_ARGUMENT_COUNT.getMessage());
            }
        } else {
            Console.getInstance().println(Error.NO_PERMISSION.getMessage());
        }
    }
}
