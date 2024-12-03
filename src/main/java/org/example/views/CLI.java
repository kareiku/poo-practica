package org.example.views;

import org.example.controllers.Controller;
import org.example.models.Error;
import org.example.models.Message;

import java.util.Scanner;

public class CLI {
    private final Controller controller;

    public CLI() {
        this.controller = new Controller();
    }

    public void start() {
        System.out.println(Message.WELCOME.getMessage());
        do {
            System.out.print(Message.INPUT_LINE.getMessage());
            Error error = this.controller.handleInput(new Scanner(System.in).nextLine());
            System.err.println(error);
        } while (!this.controller.exitHasBeenExecuted());
        System.out.println(Message.BYE.getMessage());
    }

    public static void main(String[] args) {
        new CLI().start();
    }
}
