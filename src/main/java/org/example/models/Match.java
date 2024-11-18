package org.example.models;

public class Match {
    private final Participant[] participants;

    public Match(Participant[] participants) {
        this.participants = participants;
    }

    public Participant[] getParticipants() {
        return participants;
    }
}