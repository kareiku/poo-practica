package org.example.models;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;

public class Team implements Participant {
    private final String admin;
    private final String name;
    private final Collection<Player> players;

    public Team(String admin, String name, Player... players) {
        this.admin = admin;
        this.name = name;
        this.players = new HashSet<>(Arrays.asList(players));
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
        BinaryOperator<Double> accumulate = (a, b) -> a * b;
        Arrays.stream(Category.values()).forEach(category -> {
            AtomicReference<Double> product = new AtomicReference<>(1.0);
            this.players.forEach(player -> product.accumulateAndGet(player.getStat(category), accumulate));
            statistics.put(category, Math.pow(product.get(), 1. / this.players.size()));
        });
        return statistics;
    }

    public String getStatsFormat() {
        StringBuilder format = new StringBuilder();
        this.geometricMeans().forEach((key, value) -> format.append(key.getName()).append(": ").append(value));
        return format.toString();
    }

    @Override
    public double rating() {
        return this.geometricMeans().values().stream().reduce(Double::sum).orElse(0.0);
    }

    @Override
    public String getFormat() {
        return name + " (" + admin + ")";
    }
}
