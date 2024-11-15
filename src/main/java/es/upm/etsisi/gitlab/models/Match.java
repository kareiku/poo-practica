package es.upm.etsisi.gitlab.models;

public class Match {
    private final Participant[] participants;

    public Match(Participant[] participants) {
        this.participants = participants;
    }

    public String toString() {
        return participants[0] + " vs. " + participants[1];
    }
}
