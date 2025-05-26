package org.example.utils;

public enum Error {
    NONE,
    COMMAND_NOT_FOUND("Error: command not found."),
    NO_PERMISSION("Error: Permission denied."),
    INCORRECT_ARGUMENT_COUNT("Error: Not enough arguments provided."),
    ALREADY_LOGGED_ON("Error: User already logged in the system."),
    NO_SUCH_USER("Error: That user doesn't exists in the system."),
    INCORRECT_PASSWORD("Error: Incorrect password entered."),
    NOT_LOGGED_ON("Error: Attempted to log out without being logged on."),
    INCORRECT_ARGUMENT_FORMAT("Error: Some arguments may have an incorrect format."),
    EXISTENT_PLAYER("Error: The player already exists."),
    INEXISTENT_PLAYER("Error: That player doesn't exist."),
    EXISTENT_TEAM("Error: Team with such name already exists."),
    INEXISTENT_TEAM("Error: Team with such name doesn't exist."),
    EXISTENT_TOURNAMENT("Error: Tournament with such name already exists."),
    INEXISTENT_TOURNAMENT("Error: Tournament with such name doesn't exist."),
    PARTICIPANT_ON_TOURNAMENT_IN_PROGRESS("Error: Participant is currently participating in a tournament in progress."),
    NO_SUCH_OPTION("Error: Provided option is not available."),
    PLAYER_ALREADY_IN_TEAM("Error: That player already belongs to the provided team."),
    PARTICIPANT_ALREADY_IN_TOURNAMENT("Error: That participant is already signed up in the tournament."),
    TOURNAMENT_IN_PROGRESS("Error: Provided tournament is currently in progress.");

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

    public boolean isNotNone() {
        return this != NONE;
    }
}
