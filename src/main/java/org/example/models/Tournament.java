package org.example.models;

import java.time.Instant;
import java.util.Date;

public class Tournament {
    private final String name;
    private final Date[] dates; // fixme (question) This or Date start, end; ?
    private final String league;
    private final String sport;

    public Tournament(String name, Date start, Date end, String league, String sport) {
        this.name = name;
        this.dates = new Date[2];
        this.dates[0] = start;
        this.dates[1] = end;
        this.league = league;
        this.sport = sport;
    }

    public boolean inProgress() {
        Date now = Date.from(Instant.now());
        return now.after(this.dates[0]) && now.before(this.dates[1]);
    }
}
