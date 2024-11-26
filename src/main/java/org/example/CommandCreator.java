package org.example;

import org.example.views.Command;
import org.example.views.commands.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandCreator {
    private final Map<String, Command> commands;

    public CommandCreator() {
        this.commands = new HashMap<>();
        this.displaceCommands();
    }

    private void displaceCommands() {
        final Command[] commands = {
                new ExitCommand(),
                new LoginCommand(),
                new LogoutCommand(),
                new PlayerCreateCommand(),
                new PlayerDeleteCommand(),
                new StatisticsShowCommand(),
                new TeamAddCommand(),
                new TeamCreateCommand(),
                new TeamDeleteCommand(),
                new TeamRemoveCommand(),
                new TournamentAddCommand(),
                new TournamentCreateCommand(),
                new TournamentDeleteCommand(),
                new TournamentListCommand(),
                new TournamentMatchmakingCommand(),
                new TournamentRemoveCommand()
        };

        for (Command command : commands) {
            this.commands.put(command.getName(), command);
        }
    }

    public Map<String, Command> createCommands() {
        return new HashMap<>(commands);
    }
}
