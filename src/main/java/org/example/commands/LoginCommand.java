package org.example.commands;

import org.example.models.Role;
import org.example.models.User;
import org.example.views.CommandView;

import java.util.List;

public class LoginCommand extends CommandView {
    private final List<User> users;

    public LoginCommand() {
        super("login", "<email>;<password>", "Attempts to log the specified user into the system.", Role.GUEST);
    }

    public void execute(String[] args) {
        /*
         TODO?
          - Search for the user in the database (file-based first, relational later).
          - If it's a valid user, get its privilege level.
          - Set the user privilege to role.
        */
        User user = new User(args[0], args[1]);
    }

    private boolean checkUser(User requiredUser) {
        for (User user : this.users) {
            if (user.getEmail().equals(requiredUser.getEmail())
                    &&
                    user.getPassword().equals(requiredUser.getPassword())) {
                // ...
            }
        }
        return false; // fixme
    }
}
