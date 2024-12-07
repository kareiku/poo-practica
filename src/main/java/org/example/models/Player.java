package org.example.models;

import java.util.HashMap;
import java.util.Map;

public class Player implements Participant {
    private final String forename;
    private final String surname;
    private final String DNI;
    private final Map<Category, Double> stats;
    private final User user;

    public Player(String email, String forename, String surname, String DNI, Double... stats) {
        this.forename = forename;
        this.surname = surname;
        this.DNI = DNI;
        this.stats = new HashMap<>();
        Category[] categories = Category.values();
        for (int i = 0; i < categories.length; i++) {
            this.stats.put(categories[i], stats.length > i ? stats[i] : 0.0);
        }
        this.user = new User(email, email, Role.PLAYER);
    }

    public Player(String forename, String surname, String DNI) {
        this(null, forename, surname, DNI);
    }

    public String getIdentifier() {
        return this.DNI;
    }

    public boolean isUser(User user) {
        return this.user == user;
    }

    public double getStat(Category category) {
        return this.stats.get(category);
    }

    public String getStatsFormat(String option) {
        if ("-csv".equals(option)) {
            return this.statsCSV();
        } else if ("-json".equals(option)) {
            return this.statsJSON();
        } else {
            return this.statsDefault();
        }
    }

    private String statsDefault() {
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

    private String statsCSV() {
        StringBuilder format = new StringBuilder();
        Double[] scores = this.stats.values().toArray(new Double[0]);
        for (int i = 0; i < scores.length - 1; i++) {
            format.append(scores[i]).append(',');
        }
        format.append(scores[scores.length - 1]);
        return format.toString();
    }

    private String statsJSON() {
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

    public double rating() {
        double[] rating = {0.0};
        this.stats.forEach(((category, score) -> rating[0] += score));
        return rating[0] / Category.values().length;
    }

    public String getFormat() {
        return forename + " " + surname + " (" + DNI + ")";
    }
}
