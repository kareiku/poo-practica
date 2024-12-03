package org.example.models;

import java.util.HashSet;
import java.util.Set;

public class TournamentSet {
    private final Set<Tournament> tournaments;

    public TournamentSet() {
        this.tournaments = new HashSet<>();
    }

    public void add(Tournament tournament){
        this.tournaments.add(tournament);
    }

    public void remove(Tournament tournament){
        this.tournaments.remove(tournament);
    }
}
