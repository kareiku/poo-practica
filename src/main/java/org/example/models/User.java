package es.upm.etsisi.gitlab.models;

public class User {
    private final String email;
    private final String password;
    private Role role;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Role verify(String email, String password) {
        return email.equals(this.email) && password.equals(this.password) ? role : null;
    }
}
