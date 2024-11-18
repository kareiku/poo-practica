package org.example.models;

import java.util.HashMap;
import java.util.Map;

public class Player extends Participant {
    private final User user;
    private final String surnames;
    private final String DNI;
    private final Map<Category, Double> statistics;

    public Player(String email, String password, String name, String surnames, String DNI) {
        super(name);
        this.user = new User(email, password);
        this.surnames = surnames;
        this.DNI = DNI;
        this.statistics = new HashMap<>();
    }

    public boolean equals(Player player) throws NullPointerException {
        return this.DNI.equals(player.DNI);
    }

    public String toString() {
        return String.format("%s\t%s\t%s", DNI, super.toString(), surnames);
    }

    // fixme: send to ParticipantView... or PlayerView?
    public String getStatistics(String option) {
        StringBuilder format = new StringBuilder();
        for (Map.Entry<Category, Double> statistic : statistics.entrySet()) {
            format.append("Player with DNI ")
                    .append(DNI)
                    .append(" has a score of ")
                    .append(statistic.getValue())
                    .append(" in the category ")
                    .append(statistic.getKey())
                    .append(".\n");
        }
        return format.toString();
    }
}
