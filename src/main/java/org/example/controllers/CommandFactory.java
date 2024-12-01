package org.example.controllers;

import org.example.commands.*;
import org.example.models.ParticipantSet;
import org.example.models.TournamentList;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    public Map<String, Command> createCommands(ParticipantSet participants, TournamentList tournaments) {
        final Command[] commandArray = {
                new ExitCommand("exit", "", "Saves changes and exits the application."),
                new LoginCommand(),
                new LogoutCommand(),
                new PlayerCreateCommand(participants),
                new PlayerDeleteCommand(participants),
                new StatisticsShowCommand(participants),
                new TeamAddCommand(participants),
                new TeamCreateCommand(participants),
                new TeamDeleteCommand(participants),
                new TeamRemoveCommand(participants),
                new TournamentAddCommand(tournaments),
                new TournamentCreateCommand(tournaments),
                new TournamentDeleteCommand(tournaments),
                new TournamentListCommand(tournaments),
                new TournamentMatchmakingCommand(tournaments),
                new TournamentRemoveCommand(tournaments)
        };
        final HashMap<String, Command> commands = new HashMap<>();
        for (Command command : commandArray) {
            commands.putIfAbsent(command.getName(), command);
        }
        return commands;
    }
}
