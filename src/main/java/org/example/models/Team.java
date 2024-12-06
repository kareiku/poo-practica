package org.example.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Team implements Participant {
    private final String name;
    private final String adminCreator;
    private final Set<Player> players;

    public Team(String adminCreator, String name, String... DNIs) {
        this.name = name;
        this.adminCreator = adminCreator;
        this.players = new HashSet<>();
        if (DNIs != null) {
            for (String DNI : DNIs) {
                this.players.add(new Player(null, null, DNI));
            }
        }
    }

    public String getIdentifier() {
        return this.name;
    }

    public boolean add(Player player) {
        return this.players.add(player);
    }

    public boolean remove(Player player) {
        return this.players.remove(player);
    }

    public boolean contains(Player player) {
        return this.players.contains(player);
    }

    private Map<Category, Double> geometricMeans() {
        Map<Category, Double> statistics = new HashMap<>();
        Category[] categories = Category.values();
        double product = 1.0;
        for (int i = 0; i < categories.length - 1; i++) {
            for (Player player : this.players) {
                product *= player.getStat(categories[i]);
            }
            statistics.put(categories[i], Math.pow(product, 1. / this.players.size()));
        }
        return statistics;
    }

    public String getStatsFormat() {
        StringBuilder format = new StringBuilder();
        Map<Category, Double> statistics = this.geometricMeans();
        statistics.forEach((key, value) -> format
                .append(key.getName()).append(":\t").append(value));
        return format.toString();
    }

    public double rating() {
        double[] rating = {0.0};
        this.geometricMeans().forEach(((category, score) -> rating[0] += score));
        return rating[0] / Category.values().length;
    }

    public String getFormat() {
        return name + " (" + adminCreator + ")";
    }
}
