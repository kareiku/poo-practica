package es.upm.etsisi.gitlab.views;

import es.upm.etsisi.gitlab.models.Command;

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
