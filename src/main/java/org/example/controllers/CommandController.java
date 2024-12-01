package org.example.controllers;

import org.example.commands.Command;
import org.example.models.ParticipantSet;
import org.example.models.TournamentList;
import org.example.views.CommandView;

import java.util.Map;

public class CommandController {
    private final ParticipantSet participants;
    private final TournamentList tournaments;

    private final Map<String, Command> commands;

    public CommandController(ParticipantSet participants, TournamentList tournaments) {
        this.participants = participants;
        this.tournaments = tournaments;
        this.commands = new CommandFactory().createCommands(participants, tournaments);
    }

    public void showHelp() {
        this.commands.forEach((commandName, command) -> new CommandView().showHelp(command));
    }

    public void parseCommand(String statement) {
    }

    public void execute(Command command) {
    }
}
