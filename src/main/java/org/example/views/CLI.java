package org.example.views;

import org.example.controllers.Controller;
import org.example.models.Error;
import org.example.models.Message;
import org.example.Console;

public class CLI {
    private final Controller controller;

    public CLI() {
        this.controller = new Controller();
    }

    public void start() {
        Console.getInstance().println(Message.WELCOME.getMessage());
        do {
            Console.getInstance().print(Message.INPUT_LINE.getMessage());
            Error error = this.controller.handleInput(Console.getInstance().readLine());
            System.err.println(error);
        } while (!this.controller.exitHasBeenExecuted());
        Console.getInstance().println(Message.BYE.getMessage());
    }

    public static void main(String[] args) {
        new CLI().start();
    }
}
