package org.example.models;

import java.util.HashSet;
import java.util.Set;

public class Team extends Participant {
    private final Set<Player> players;

    public Team(String name) {
        super(name);
        this.players = new HashSet<>();
    }

    protected void rate(Category category, double score) {
    }

    public void add(Player player) {
        this.players.add(player);
    }

    public void remove(Player player) {
        this.players.remove(player);
    }

    // fixme don't think this should be here
    public double[] geometricMeans() {
        double[] means = new double[Category.values().length];
        double product = 1;
        for (int i = 0; i < means.length; i++) {
            for (Player player : players) {
                product *= player.statsIn(Category.values()[i]);
            }
            means[i] = Math.pow(product, 1. / players.size());
        }
        return means;
    }
}
