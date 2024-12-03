package org.example.models;

import java.util.LinkedList;
import java.util.List;

public class TournamentList {
    private final List<Tournament> tournaments;

    public TournamentList() {
        this.tournaments = new LinkedList<>();
    }

    public void add(Tournament tournament) {
        this.tournaments.add(tournament);
    }

    public void remove(Tournament tournament) {
        this.tournaments.remove(tournament);
    }
}
