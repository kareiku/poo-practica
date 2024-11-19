package org.example.app;

import org.example.models.ParticipantSet;
import org.example.models.TournamentList;
import org.example.views.Message;
import org.example.views.CLI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SportsManagementSystem {
    public static void main(String[] args) {
        new SportsManagementSystem().start();
    }

    private void start() {
        CLI cli = new CLI(new ParticipantSet(), new TournamentList());
        this.pullInitialData(cli);
        cli.readUntilExit();
    }

    private void pullInitialData(CLI cli) {
        Message.LOAD_INITIAL_DATA.write();

        BufferedReader inputFile = null;

        try {
            inputFile = new BufferedReader(new FileReader("initialData.txt"));

            String line;
            while ((line = inputFile.readLine()) != null) {
                cli.scan(line);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (inputFile != null) {
                try {
                    inputFile.close();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
