package es.upm.etsisi.gitlab.commands.user;

import es.upm.etsisi.gitlab.models.Command;

public class ExitCommand extends Command {
    private boolean executed;

    public ExitCommand() {
        super(
                "exit",
                "",
                "Exits the application, regardless of the current state. It does save changes."
        );
    }

    public void run(String[] args) {
        executed = true;
    }

    public boolean hasBeenExecuted() {
        return executed;
    }
}
