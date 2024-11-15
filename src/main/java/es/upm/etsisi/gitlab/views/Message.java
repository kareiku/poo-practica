package es.upm.etsisi.gitlab.views;

public enum Message {
    WELCOME("Welcome to the Sport's Management System.\nWrite a command to start managing your players."),
    INPUT_LINE("> "),
    LOAD_INITIAL_DATA("Loading initial data..."),
    BYE("Exiting the application...");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public void write() {
        System.out.println(message);
    }
}
