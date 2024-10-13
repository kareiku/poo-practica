package project.manager;

public enum Message {
    WELCOME("Welcome to the Sport's Management System.\nWrite a command to start managing your players."),
    EXIT_WITH("To kill the process, type "),
    INPUT("> ");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
