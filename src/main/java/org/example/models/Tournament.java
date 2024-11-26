package org.example.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tournament {
    private final String name;
    private final Date start;
    private final Date end;
    private final String league;
    private final String sport;
    private final List<Participant> participants;

    public Tournament(String name, Date start, Date end, String league, String sport) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.league = league;
        this.sport = sport;
        this.participants = new ArrayList<>();
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    @Deprecated
    public void write() {
        System.out.println(league + "\t" + name + "\t" + sport + "\t" + start + "\t" + end);
    }
}
