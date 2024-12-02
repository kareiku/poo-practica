package org.example.commands;

import org.example.views.CommandView;

import java.util.Iterator;

public class StatisticsShowCommand extends Command {
    public StatisticsShowCommand(String name, String usage, String description) {
        super(name, usage, description);
    }

    public void execute(String[] args) {
        StringBuilder message = new StringBuilder();
        Iterator<Participant> iterator = participant.getStatistics().iterator();
        while (iterator.hasNext()) {
            message.append(iterator.next());
        }
        new CommandView().write(message.toString());
    }
}
