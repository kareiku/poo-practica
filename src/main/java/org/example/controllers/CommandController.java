package org.example.controllers;

import org.example.views.commands.Command;
import org.example.Error;

public class CommandController {
    public Error execute(Command command, String[] args) {
        return command.execute(args);
    }
}
