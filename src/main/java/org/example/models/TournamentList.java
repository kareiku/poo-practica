package org.example.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TournamentList {
    private final List<Tournament> tournaments;

    public TournamentList() {
        this.tournaments = new ArrayList<>();
    }

    public void add(Tournament tournament) {
        this.tournaments.add(tournament);
    }

    public void remove(Tournament tournament) {
        this.tournaments.remove(tournament);
    }

    public Iterator<Tournament> iterator() {
        return this.tournaments.iterator();
    }
}
