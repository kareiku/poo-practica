package org.example.models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        long now = System.currentTimeMillis();
        return now > this.toEpochMillis(this.startDate) && now < this.toEpochMillis(this.endDate);
    }

    public boolean hasFinished() {
        return System.currentTimeMillis() > this.toEpochMillis(this.endDate);
    }

    private long toEpochMillis(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
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
        format.append(String.format("Tournament \"%s\"%nFrom: %s, until: %s.%n%s league: \"%s\"%n", name, startDate, endDate, sport, league));
        this.matchups.forEach((match -> format.append(match.getFormat()).append(System.lineSeparator())));
        return format.toString();
    }

    public void manualMatchmake(Participant... participants) {
        if (participants != null) {
            IntStream.range(0, participants.length - 2)
                    .mapToObj(i -> new AbstractMap.SimpleEntry<>(participants[i], participants[i + 1]))
                    .collect(Collectors.toList())
                    .forEach(pair -> this.matchups.add(new Match(pair.getKey(), pair.getValue())));
        }
    }

    public void randomMatchmake(Participant... participants) {
        if (participants != null) {
            Random random = new Random();
            List<Participant> participantList = Arrays.asList(participants);
            IntStream.range(0, participantList.size() / 2).forEach(i -> this.matchups.add(new Match(
                    participantList.remove(random.nextInt(participantList.size())),
                    participantList.remove(random.nextInt(participantList.size()))
            )));
        }
    }

    public String getSortedParticipantsFormat() {
        StringBuilder format = new StringBuilder();
        this.participants.stream()
                .sorted(Comparator.comparingDouble(Participant::rating))
                .forEach(participant -> format.append(participant.getFormat()).append(System.lineSeparator()));
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