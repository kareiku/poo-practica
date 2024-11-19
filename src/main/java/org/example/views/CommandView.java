package org.example.views;

import org.example.models.Command;

public class CommandView {
    public void write(Command command) {
        System.out.printf("%s\nUsage: %s %s\n%s\n\n",
                command.name(),
                command.name(),
                command.usage(),
                command.help());
    }
}
