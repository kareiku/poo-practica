package org.example.models;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Tournament {
    private final String name;
    private final Date start;
    private final Date end;
    private final String league;
    private final String sport;
    private final Set<Participant> participants;

    public Tournament(String name, Date start, Date end, String league, String sport) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.league = league;
        this.sport = sport;
        this.participants = new HashSet<>();
    }

    public String getName() {
        return this.name;
    }

    public boolean inProgress() {
        Date now = Date.from(Instant.now());
        return now.after(this.start) && now.before(this.end);
    }

    public boolean hasFinished() {
        Date now = Date.from(Instant.now());
        return now.after(this.end);
    }

    public boolean add(Participant participant) {
        return this.participants.add(participant);
    }

    public boolean remove(Participant participant) {
        return this.participants.remove(participant);
    }

    public boolean contains(Participant participant) {
        return this.participants.contains(participant);
    }

    public String getFormat() {
        return String.format("%s\t[%s]-[%s]: %s, %s", name, start.toString(), end.toString(), league, sport);
    }
}
