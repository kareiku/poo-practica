package org.example.models;

public abstract class Participant {
    private final String name;

    public Participant(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
