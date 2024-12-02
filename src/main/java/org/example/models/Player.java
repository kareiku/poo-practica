package org.example.models;

public class Player extends Participant {
    private final String surname;
    private final String DNI;

    public Player(String name, String surname, String DNI) {
        super(name);
        this.surname = surname;
        this.DNI = DNI;
    }

    public void rate(Category category, double score) {
        this.getStatistics().replace(category, score);
    }

    public boolean equals(Player player) {
        return this.DNI.equals(player.DNI);
    }

    // fixme what.
    public double statsIn(Category category) {
        return this.getStatistics().get(category);
    }

    // fixme: send to ParticipantView... or PlayerView?
    public String getStatistics(String option) {
        StringBuilder statistics = new StringBuilder();
        this.getStatistics().forEach((value, key) -> statistics
                .append("Player with DNI ")
                .append(this.DNI)
                .append(" has a score of ")
                .append(value)
                .append(" in the category ")
                .append(key)
                .append(".\n"));
        return statistics.toString();
    }
}
