package org.example.models;

import java.util.HashSet;

public class ParticipantSet extends HashSet<Participant> {
    public ParticipantSet() {
    }

    public ParticipantSet(HashSet<Participant> set) {
        super(set);
    }

    @Deprecated
    public String toString() {
        StringBuilder format = new StringBuilder("DNI\tSurname\tName\n");
        for (Participant participant : this) {
            format.append(participant).append("\n");
        }
        return format.toString();
    }
}
