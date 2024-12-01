package org.example.commands;

import org.example.models.Participant;
import org.example.views.CommandView;

import java.util.Iterator;

public class StatisticsShowCommand extends Command {
    private final Participant participant;

    public StatisticsShowCommand(Participant participant) {
        super("statistics-show", "", "Shows the statistics of the logged player or their team, in their case.");
        this.participant = participant;
    }

    public void execute(String[] args) {
        StringBuilder message = new StringBuilder();
        Iterator<Participant> iterator = participants.iterator();
        while (iterator.hasNext()) {
            message.append(iterator.next());
        }
        new CommandView().write(message.toString());
    }
}
