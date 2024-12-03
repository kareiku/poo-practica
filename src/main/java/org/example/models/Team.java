package org.example.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Team extends Participant {
    private final Set<Player> players;

    public Team(String name) {
        super(name);
        this.players = new HashSet<>();
    }

    public void add(Player player) {
        this.players.add(player);
    }

    public void remove(Player player) {
        this.players.remove(player);
    }

    private Map<Category, Double> geometricMeans() {
        Map<Category, Double> statistics = new HashMap<>();
        Category[] categories = Category.values();
        double product = 1;
        for (int i = 0; i < categories.length - 1; i++) {
            for (Player player : players) {
                product *= player.ratingIn(categories[i]);
            }
            statistics.put(categories[i], Math.pow(product, 1. / players.size()));
        }
        return statistics;
    }

    public String statisticsFormat(String option) {
        StringBuilder format = new StringBuilder();
        Map<Category, Double> statistics = this.geometricMeans();
        statistics.forEach((key, value) -> format
                .append(key.getName()).append(":\t").append(value));
        return format.toString();
    }
}
