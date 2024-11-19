package org.example.commands;

import org.example.models.Command;
import org.example.models.ParticipantSet;

public class StatisticsShowCommand extends Command {
    private final ParticipantSet participants;

    public StatisticsShowCommand(ParticipantSet participants) {
        super(
                "statistics-show",
                "",
                "Shows the statistics of the logged player or their team, in their case."
        );

        this.participants = participants;
    }

    public void run(String[] args) {
    }
}
