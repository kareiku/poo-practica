package org.example.views;

import org.example.commands.Command;

public class CommandView {
    public void showHelp(Command command) {
        System.out.printf("%s\nUsage: %s %s\n%s\n\n",
                command.getName(),
                command.getName(),
                command.getUsage(),
                command.getDescription());
    }

    public void write(String message) {
        System.out.print(message);
    }
}
