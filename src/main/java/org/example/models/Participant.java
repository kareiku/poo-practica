package org.example.models;

import java.util.HashMap;
import java.util.Map;

public abstract class Participant {
    private final String name;
    private final Map<Category, Double> statistics;

    public Participant(String name) {
        this.name = name;
        this.statistics = new HashMap<>();
    }

    public Map<Category, Double> getStatistics() {
        return statistics;
    }

    public boolean equals(Participant participant) {
        return this.name.equals(participant.name);
    }

    public abstract String statisticsFormat(String options);
}
