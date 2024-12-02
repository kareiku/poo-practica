package org.example.commands;

import org.example.models.Database;

import java.util.Deque;

public class PlayerCreateCommand extends Command {
    public PlayerCreateCommand(String name, String usage, String description) {
        super(name, usage, description);
    }

    public void execute(String[] args) {
        Database database = new Database("players.csv");
        Deque<String> jugadores = database.loadData();
        String[] jugador;
        boolean encontrado = false; //fixme exec.refactorName()
        while (!jugadores.isEmpty() && !encontrado) {
            jugador = jugadores.pop().split(",");
            encontrado = jugador[3].equals(args[0]);
        }
        if (!encontrado) {
            database.storeData(String.format(",%s,%s,%s,0|0|0|0|0", args[1], args[2], args[0]));
        }
    }
}
