package es.upm.etsisi.gitlab.models;

public abstract class Participant {
    private final String name;

    public Participant(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
