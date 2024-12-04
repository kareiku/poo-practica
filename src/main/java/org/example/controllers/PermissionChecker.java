package org.example.controllers;

import org.example.models.Role;
import org.example.views.commands.*;

import java.util.HashMap;
import java.util.Map;

public class PermissionChecker {
    private final Map<String, Role[]> associations;

    public PermissionChecker() {
        this.associations = new HashMap<>();
        this.associateCommands();
    }

    public boolean hasPermission(Command command, Role userRole) {
        boolean hasPermission = false;
        for (Role role : this.associations.get(command.getName())) {
            if (!hasPermission) {
                hasPermission = userRole == role;
            }
        }
        return hasPermission;
    }

    private void associate(String commandName, Role... roles) {
        this.associations.put(commandName, roles);
    }

    private void associateCommands() {
        this.associate("exit", Role.ADMIN, Role.PLAYER, Role.GUEST);
        this.associate("login", Role.ADMIN, Role.PLAYER, Role.GUEST);
        this.associate("logout", Role.ADMIN, Role.PLAYER);
        this.associate("player-create", Role.ADMIN);
        this.associate("delete-create", Role.ADMIN);
        this.associate("statistics-show", Role.PLAYER);
        this.associate("team-add", Role.ADMIN);
        this.associate("team-create", Role.ADMIN);
        this.associate("team-delete", Role.ADMIN);
        this.associate("team-remove", Role.ADMIN);
        this.associate("tournament-add", Role.PLAYER);
        this.associate("tournament-create", Role.ADMIN);
        this.associate("tournament-delete", Role.ADMIN);
        this.associate("tournament-list", Role.ADMIN, Role.PLAYER, Role.GUEST);
        this.associate("tournament-matchmaking", Role.ADMIN);
        this.associate("tournament-remove", Role.PLAYER);
    }
}
