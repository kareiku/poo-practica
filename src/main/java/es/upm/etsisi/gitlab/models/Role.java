package es.upm.etsisi.gitlab.models;

public enum Role {
    ADMIN,
    PLAYER,
    USER;

    Role() {
    }

    public boolean isRole(Role role) {
        return this == role;
    }

    public boolean hasPermission(Role role) {
        return role.ordinal() <= this.ordinal();
    }
}
