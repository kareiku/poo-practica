package org.example;

import org.example.models.ParticipantSet;
import org.example.models.TournamentList;
import org.example.views.CLI;

public class SportsManagementSystem {
    public static void main(String[] args) {
        new SportsManagementSystem().start();
    }

    private void start() {
        CLI cli = new CLI(new ParticipantSet(), new TournamentList());
        cli.readUntilExit();
    }
}
