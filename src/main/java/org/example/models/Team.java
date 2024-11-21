package org.example.models;

import java.util.LinkedList;

public class Team extends LinkedList<Player> {
    private final String name;

    public Team(String name) {
        this.name = name;
    }

    @Deprecated
    public String toString() {
        StringBuilder format = new StringBuilder("Team " + name + " composition: ");
        for (Player player : this) {
            format.append(player);
        }
        format.append(".");
        return format.toString();
    }
}
