package org.example.models;

import java.util.HashMap;
import java.util.Map;

public class Player extends Participant {
    private final User user;
    private final String surname;
    private final String DNI;
    private final Map<Category, Double> statistics;

    public Player(String email, String password, String name, String surname, String DNI) {
        super(name);
        this.user = new User(email, password);
        this.surname = surname;
        this.DNI = DNI;
        this.statistics = new HashMap<>();
    }

    public void rate(Category category, double score) {
        this.statistics.replace(category, score);
    }

    public boolean equals(Player player) {
        return this.DNI.equals(player.DNI);
    }

    // fixme what.
    public double statsIn(Category category) {
        return this.statistics.get(category);
    }

    // fixme: send to ParticipantView... or PlayerView?
    public String getStatistics(String option) {
        StringBuilder format = new StringBuilder();
        this.statistics.forEach((value, key) -> format
                .append("Player with DNI ")
                .append(this.DNI)
                .append(" has a score of ")
                .append(value)
                .append(" in the category ")
                .append(key)
                .append(".\n"));
        return format.toString();
    }
}
