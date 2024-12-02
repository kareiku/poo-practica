package org.example.views;

import org.example.controllers.CommandController;
import org.example.models.Error;
import org.example.models.Message;

import java.util.Scanner;

public class CLI {
    private final CommandController commandController = new CommandController(); // fixme (question) Do I really need to make empty constructors for little things like this?

    public void start() {
        System.out.println(Message.WELCOME.message);
        do {
            System.out.print(Message.INPUT_LINE.message); // fixme Being message completely static... is it okay to have public attributes?
            Error error = this.commandController.handleInput(new Scanner(System.in).nextLine());
            System.err.println(error);
        } while (!this.commandController.exitHasBeenExecuted());
        System.out.println(Message.BYE.message);
    }
}
