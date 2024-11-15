package es.upm.etsisi.gitlab.models;

import java.util.ArrayList;

public class TournamentList extends ArrayList<Tournament> {
    public TournamentList() {
    }

    public void write() {
        System.out.println("League\tTournament\tSport\tStart\tEnd");
        for (Tournament tournament : this) {
            tournament.write();
        }
    }
}
