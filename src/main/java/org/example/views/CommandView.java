package org.example.views;

import org.example.models.Command;

public class CommandView {
    public CommandView() {
    }

    public void write(Command command) {
        System.out.printf("%s\n\nUsage: %s %s\n\n%s\n",
                command.getName(),
                command.getName(),
                command.getUsage(),
                command.getDescription());
    }
}
