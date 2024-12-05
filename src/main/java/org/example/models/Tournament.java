package org.example.models;

import java.time.Instant;
import java.util.*;

public class Tournament {
    private final String name;
    private final String startDate;
    private final String endDate;
    private final String league;
    private final String sport;
    private final Set<Participant> participants;
    private final List<Match> matchups;

    public Tournament(String name, String startDate, String endDate, String league, String sport) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.league = league;
        this.sport = sport;
        this.participants = new HashSet<>();
        this.matchups = new LinkedList<>();
    }

    public String getName() {
        return this.name;
    }

    public boolean inProgress() {
        Date now = Date.from(Instant.now());
        return now.after(this.toDate(this.startDate)) && now.before(this.toDate(this.endDate));
    }

    public boolean hasFinished() {
        Date now = Date.from(Instant.now());
        return now.after(this.toDate(this.endDate));
    }

    private Date toDate(String date){
        return Date.from(Instant.parse(date));
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
        return String.format("Tournament \"%s\"\nFrom: %s, until: %s.\n%s league: \"%s\"", name, startDate.toString(), endDate.toString(), sport, league);
    }

    public void manualMatchmake(Participant... participants) {
        List<Participant> participantList = Arrays.asList(participants);
        for (int i = 0; i < participantList.size() / 2; i++) {
            this.matchups.add(new Match(participantList.remove(0), participantList.remove(0)));
        }
    }

    public void randomMatchmake(Participant... participants) {
        Random random = new Random();
        List<Participant> participantList = Arrays.asList(participants);
        for (int i = 0; i < participantList.size() / 2; i++) {
            this.matchups.add(new Match(
                    participantList.remove(random.nextInt(participantList.size())),
                    participantList.remove(random.nextInt(participantList.size()))
            ));
        }
    }
}

class Match {
    private Participant[] participants;

    Match(Participant... participants) {
        this.participants = new Participant[]{participants[0], participants[1]};
    }
}
