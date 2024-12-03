package org.example.models;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Tournament {
    private final String name;
    private final Date[] dates; // fixme (question) This or Date start, end; ?
    private final String league;
    private final String sport;
    private final Set<Participant> participants;

    public Tournament(String name, Date start, Date end, String league, String sport) {
        this.name = name;
        this.dates = new Date[2];
        this.dates[0] = start;
        this.dates[1] = end;
        this.league = league;
        this.sport = sport;
        this.participants = new HashSet<>();
    }

    public boolean inProgress() {
        Date now = Date.from(Instant.now());
        return now.after(this.dates[0]) && now.before(this.dates[1]);
    }

    public void add(Participant participant) {
        this.participants.add(participant);
    }

    public void remove(Participant participant) {
        this.participants.remove(participant);
    }
}
