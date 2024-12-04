package org.example.models;

public abstract class Participant {
    private final String name;

    public Participant(String name) {
        this.name = name;
    }

    public boolean equals(Participant participant) {
        return this.name.equals(participant.name);
    }

    public abstract String statisticsFormat(String option);
}
