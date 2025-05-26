package org.example.models;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Player implements Participant {
    private final String email;
    private final String forename;
    private final String surname;
    private final String DNI;
    private final Map<Category, Double> stats;

    public Player(String email, String forename, String surname, String DNI, Double... stats) {
        this.email = email;
        this.forename = forename;
        this.surname = surname;
        this.DNI = DNI;
        this.stats = Arrays.stream(Category.values())
                .map(i -> new AbstractMap.SimpleEntry<>(i, stats.length > Arrays.asList(Category.values())
                        .indexOf(i) ? stats[Arrays.asList(Category.values())
                        .indexOf(i)] : 0.0))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public boolean isUser(User user) {
        return user.email().equals(this.email());
    }

    public String email() {
        return this.email;
    }

    public String forename() {
        return this.forename;
    }

    public String surname() {
        return this.surname;
    }

    public String DNI() {
        return this.DNI;
    }

    public Map<Category, Double> stats() {
        return this.stats;
    }

    public double getStat(Category category) {
        return this.stats.get(category);
    }

    public String getStatsFormat(String option) {
        if (option == null) {
            return this.statsDefault();
        } else switch (option) {
            case "-csv":
                return this.statsCSV();
            case "-json":
                return this.statsJSON();
            default:
                return this.statsDefault();
        }
    }

    private String statsDefault() {
        StringBuilder format = new StringBuilder();
        this.stats.forEach((category, stat) -> format.append(String.format("The player with DNI %s has a score of %.2f in the category %s.%n", this.DNI, stat, category.getName())));
        return format.toString();
    }

    private String statsCSV() {
        StringBuilder format = new StringBuilder();
        this.stats.values().forEach(score -> format.append(score).append(','));
        return format.substring(0, format.length() - 1);
    }

    private String statsJSON() {
        StringBuilder format = new StringBuilder();
        format.append('{').append(System.lineSeparator());
        this.stats.forEach((category, stat) -> format.append(String.format("\t\"%s\": %f,%n", category.getName(), stat)));
        format.deleteCharAt(format.lastIndexOf(","));
        format.append('}').append(System.lineSeparator());
        return format.toString();
    }

    @Override
    public double rating() {
        return this.stats.values().stream().reduce(Double::sum).orElse(0.0);
    }

    @Override
    public String getFormat() {
        return forename + " " + surname + " (" + DNI + ")";
    }
}
