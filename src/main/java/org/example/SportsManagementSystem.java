package org.example;

import org.example.views.CLI;

public class SportsManagementSystem {
    public static void main(String[] args) {
        new SportsManagementSystem().exec();
    }

    private void exec() {
        CLI cli = new CLI();
        cli.start();
    }
}
