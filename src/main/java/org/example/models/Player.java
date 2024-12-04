package org.example.models;

import java.util.HashMap;
import java.util.Map;

public class Player implements Participant {
    private final String forename;
    private final String surnames;
    private final String DNI;
    private final Map<Category, Double> statistics;

    public Player(String forename, String surnames, String DNI) {
        this.forename = forename;
        this.surnames = surnames;
        this.DNI = DNI;
        this.statistics = new HashMap<>();
    }

    public boolean matches(String identifier) {
        return this.DNI.equals(identifier);
    }

    public double ratingIn(Category category) {
        return this.statistics.get(category);
    }

    public String statisticsFormat(String option) {
        if (option.equals("-csv")) {
            return this.statisticsCSV();
        } else if (option.equals("-json")) {
            return this.statisticsJSON();
        } else {
            return this.statisticsDefault();
        }
    }

    private String statisticsDefault() {
        StringBuilder format = new StringBuilder();
        this.statistics.forEach((key, value) -> format
                .append("Player with DNI ")
                .append(this.DNI)
                .append(" has a score of ")
                .append(value)
                .append(" in the category ")
                .append(key.getName())
                .append(".\n"));
        return format.toString();
    }

    private String statisticsCSV() {
        StringBuilder format = new StringBuilder();
        Double[] ratings = this.statistics.values().toArray(new Double[0]);
        for (int i = 0; i < ratings.length - 1; i++) {
            format.append(ratings[i]).append(',');
        }
        format.append(ratings[ratings.length - 1]);
        return format.toString();
    }

    private String statisticsJSON() {
        StringBuilder format = new StringBuilder();
        Category[] categories = this.statistics.keySet().toArray(new Category[0]);
        Double[] scores = this.statistics.values().toArray(new Double[0]);
        format.append('{').append('\n');
        for (int i = 0; i < categories.length - 1; i++) {
            format.append(String.format("\t\"%s\": %f,\n", categories[i].getName(), scores[i]));
        }
        format.append(String.format("\t\"%s\": %f\n", categories[categories.length - 1].getName(), scores[categories.length]));
        format.append('}').append('\n');
        return format.toString();
    }
}
