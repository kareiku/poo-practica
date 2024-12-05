package org.example.views.commands;

import org.example.controllers.Controller;
import org.example.models.Role;
import org.example.utils.Error;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TournamentCreateCommand extends Command {

    public TournamentCreateCommand(Controller controller) {
        super(controller, 5, Error.NO_PERMISSION, Role.ADMIN);
    }

    protected Error executeTemplate(String[] args) {
        final String dateFormat = "\\d{2}-\\d{2}-\\d{4}";
        if (args[1].matches(dateFormat) && args[2].matches(dateFormat)) {
            return this.getController().createTournament(args[0], args[1], args[2], args[3], args[4]);
        } else {
            return Error.INCORRECT_ARGUMENT_FORMAT;
        }
    }

    private Date toDate(String date) {
        return Date.from(LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atZone(ZoneId.systemDefault()).toInstant());
    }
}
