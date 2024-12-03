package org.example.models;

import java.util.HashSet;
import java.util.Set;

public class ParticipantSet {
    private final Set<Participant> participants;

    public ParticipantSet() {
        this.participants = new HashSet<>();
    }

    public void add(Participant participant) {
        this.participants.add(participant);
    }

    public void remove(Participant participant) {
        this.participants.remove(participant);
    }
}
