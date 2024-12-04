package org.example;

public enum Error {
    NONE("Todo correcto ü"),
    COMMAND_NOT_FOUND("Error: command not found."),
    NO_PERMISSION("Error: Permission denied."),
    EXISTENT_PLAYER("Error: The player already exists."),
    INEXISTENT_PLAYER("Error: That player doesn't exist."),
    ALREADY_LOGGED("Error: User already logged in the system."),
    NO_SUCH_USER("Error: That user doesn't exists in the system.");

    private final String message;

    Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
