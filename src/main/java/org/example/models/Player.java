package org.example.models;

import java.util.HashMap;
import java.util.Map;

public class Player implements Participant {
    private final String forename;
    private final String surname;
    private final String DNI;
    private final Map<Category, Double> stats;
    private final User user;

    public Player(String forename, String surname, String DNI) {
        this.forename = forename;
        this.surname = surname;
        this.DNI = DNI;
        this.stats = new HashMap<>();
        for (Category category : Category.values()) {
            this.stats.put(category, 0.0);
        }
        this.user = null;
    }

    public boolean isUser(User user) {
        return this.user == user;
    }

    public boolean matches(String DNI) {
        return this.DNI.equals(DNI);
    }

    public double getStat(Category category) {
        return this.stats.get(category);
    }

    public String getStatsFormat(String option) {
        if ("-csv".equals(option)) {
            return this.statisticsCSV();
        } else if ("-json".equals(option)) {
            return this.statisticsJSON();
        } else {
            return this.statisticsDefault();
        }
    }

    private String statisticsDefault() {
        StringBuilder format = new StringBuilder();
        this.stats.forEach((key, value) -> format
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
        Double[] scores = this.stats.values().toArray(new Double[0]);
        for (int i = 0; i < scores.length - 1; i++) {
            format.append(scores[i]).append(',');
        }
        format.append(scores[scores.length - 1]);
        return format.toString();
    }

    private String statisticsJSON() {
        StringBuilder format = new StringBuilder();
        Category[] categories = this.stats.keySet().toArray(new Category[0]);
        Double[] scores = this.stats.values().toArray(new Double[0]);
        format.append('{').append('\n');
        for (int i = 0; i < categories.length - 1; i++) {
            format.append(String.format("\t\"%s\": %f,\n", categories[i].getName(), scores[i]));
        }
        format.append(String.format("\t\"%s\": %f\n", categories[categories.length - 1].getName(), scores[scores.length - 1]));
        format.append('}').append('\n');
        return format.toString();
    }
}
