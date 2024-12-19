package org.example.models;

import java.time.Instant;
import java.util.*;

public class Tournament {
    private final String name;
    private final String startDate;
    private final String endDate;
    private final String league;
    private final String sport;
    private final Collection<Participant> participants;
    private final Collection<Match> matchups;

    public Tournament(String name, String startDate, String endDate, String league, String sport) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.league = league;
        this.sport = sport;
        this.participants = new HashSet<>();
        this.matchups = new ArrayList<>();
    }

    public String name() {
        return this.name;
    }

    public String startDate() {
        return this.startDate;
    }

    public String endDate() {
        return this.endDate;
    }

    public String league() {
        return this.league;
    }

    public String sport() {
        return this.sport;
    }

    public boolean isInProgress() {
        Date now = Date.from(Instant.now());
        return now.after(this.toDate(this.startDate)) && now.before(this.toDate(this.endDate));
    }

    public boolean hasFinished() {
        Date now = Date.from(Instant.now());
        return now.after(this.toDate(this.endDate));
    }

    private Date toDate(String date) {
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
        StringBuilder format = new StringBuilder();
        format.append(String.format("Tournament \"%s\"\nFrom: %s, until: %s.\n%s league: \"%s\"", name, startDate, endDate, sport, league));
        this.matchups.forEach((match -> format.append(match.getFormat()).append("\n")));
        return format.toString();
    }

    public void manualMatchmake(Participant... participants) {
        List<Participant> participantList = Arrays.asList(participants);
        for (int i = 0; i < participantList.size() / 2; i++) {
            this.matchups.add(new Match(participantList.remove(0), participantList.remove(0)));
        }
    }

    public void randomMatchmake(Participant... participants) {
        List<Participant> participantList = Arrays.asList(participants);
        for (int i = 0; i < participantList.size() / 2; i++) {
            this.matchups.add(new Match(
                    participantList.remove((int) (Math.random() * participantList.size())),
                    participantList.remove((int) (Math.random() * participantList.size()))
            ));
        }
    }

    public String getSortedParticipantsFormat() {
        StringBuilder format = new StringBuilder();
        List<Participant> sortedParticipants = new ArrayList<>(this.participants);
        sortedParticipants.sort(Comparator.comparingDouble(Participant::rating));
        sortedParticipants.forEach((participant -> format.append(participant.asString()).append(("\n"))));
        return format.toString();
    }

    private static class Match {
        private final Participant[] participants;

        Match(Participant... participants) {
            this.participants = new Participant[]{participants[0], participants[1]};
        }

        String getFormat() {
            return participants[0] + " vs. " + participants[1];
        }
    }
}