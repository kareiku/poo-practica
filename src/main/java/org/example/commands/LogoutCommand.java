package es.upm.etsisi.gitlab.commands.user;

import es.upm.etsisi.gitlab.models.Command;

public class LogoutCommand extends Command {
    public LogoutCommand() {
        super(
                "logout",
                "",
                "Logs out the logged user from the system."
        );
    }

    public void run(String[] args) {
        // TODO
    }
}
