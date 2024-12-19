package org.example.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Team implements Participant {
    private final String admin;
    private final String name;
    private final Collection<Player> players;

    public Team(String admin, String name, String... DNIs) {
        this.admin = admin;
        this.name = name;
        this.players = new HashSet<>();
        if (DNIs != null) {
            for (String DNI : DNIs) {
                this.players.add(new Player(null, null, DNI));
            }
        }
    }

    public String admin() {
        return this.admin;
    }

    public String name() {
        return this.name;
    }

    public Collection<Player> players() {
        return this.players;
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

    public String asString() {
        return name + " (" + admin + ")";
    }
}
