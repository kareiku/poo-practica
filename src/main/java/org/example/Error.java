package org.example;

public enum Error {
    NONE,
    COMMAND_NOT_FOUND("Error: command not found."),
    NO_PERMISSION("Error: Permission denied."),
    INCORRECT_ARGUMENT_COUNT("Error: Not enough arguments provided."),
    ALREADY_LOGGED_ON("Error: User already logged in the system."),
    NO_SUCH_USER("Error: That user doesn't exists in the system."),
    INCORRECT_ARGUMENT_FORMAT("Error: SOme arguments ay be having an incorrect format."),
    EXISTENT_PLAYER("Error: The player already exists."),
    INEXISTENT_PLAYER("Error: That player doesn't exist.");

    private final String message;

    Error() {
        this(null);
    }

    Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
