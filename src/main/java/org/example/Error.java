package org.example;

public enum Error {
    NONE,
    COMMAND_NOT_FOUND("Error: command not found."),
    NO_PERMISSION("Error: Permission denied."),
    NO_SUCH_USER("Error: That user doesn't exists in the system."),
    ALREADY_LOGGED("Error: User already logged in the system."),
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
