package org.example.views;

import org.example.commands.*;
import org.example.models.*;

import java.util.*;

public class CLI {
    private final ParticipantSet participants;
    private final TournamentList tournaments;
    private final Map<Role, List<Command>> commandMap;
    private final List<Command> allowedCommands;
    private final ExitCommand exitCommand;
    private final LoginCommand loginCommand;
    private final LogoutCommand logoutCommand;
    private final CommandView commandView;

    public CLI(ParticipantSet participants, TournamentList tournaments) {
        this.participants = participants;
        this.tournaments = tournaments;
        commandMap = new HashMap<>();
        allowedCommands = new ArrayList<>();
        exitCommand = new ExitCommand();
        loginCommand = new LoginCommand();
        logoutCommand = new LogoutCommand();
        commandView = new CommandView();

        commandMap.put(Role.ADMIN, createAdminCommandList());
        commandMap.put(Role.PLAYER, createPlayerCommandList());
        commandMap.put(Role.USER, createUserCommandList());
    }

    public void readUntilExit() {
        Message.WELCOME.write();
        do {
            Message.INPUT_LINE.write();
            this.scan(new Scanner(System.in).nextLine());
        } while (!exitCommand.hasBeenExecuted());
        Message.BYE.write();
    }

    public void scan(String statement) {
        String commandName = statement.split("\\s+")[0];
        String[] args = statement.split("\\s+")[1].split(";");
        for (Role role : commandMap.keySet()) {
            if (loginCommand.getRole().hasPermission(role)) {
                allowedCommands.addAll(commandMap.get(role));
            }
        }
        // TODO


        Command command = null;
        Iterator<Command> commandIterator = allowedCommands.iterator();
        boolean found = false;

        while (!found && commandIterator.hasNext()) {
            command = commandIterator.next();
            found = command.matches(commandName);
        }

        if (found) {
            command.run(args);
        } else {
            Error.UNKNOWN_COMMAND_ERROR.write();
        }
    }

    private List<Command> createAdminCommandList() {
        return new ArrayList<>(Arrays.asList(
                new TeamAddCommand(participants),
                new PlayerCreateCommand(participants),
                new TeamCreateCommand(participants),
                new TournamentCreateCommand(tournaments),
                new PlayerDeleteCommand(participants),
                new TeamDeleteCommand(participants),
                new TournamentDeleteCommand(tournaments),
                new TeamRemoveCommand(participants),
                new TournamentMatchmakingCommand(participants, tournaments)
        ));
    }

    private List<Command> createPlayerCommandList() {
        return new ArrayList<>(Arrays.asList(
                new TournamentAddCommand(participants, tournaments),
                new TournamentRemoveCommand(participants, tournaments),
                new StatisticsShowCommand(participants)
        ));
    }

    private List<Command> createUserCommandList() {
        return new ArrayList<>(Arrays.asList(
                new ExitCommand(),
                new LoginCommand(),
                new LogoutCommand(),
                new TournamentListCommand(loginCommand.getRole(), tournaments)
        ));
    }

    private void showHelp() {
        for (Command command : allowedCommands) {
            System.out.printf("%s\n%s\n\n", command.getName(), command.getDescription());
        }
    }
}
