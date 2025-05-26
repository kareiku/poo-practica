package org.example.utils;

import java.util.Scanner;

public class Console {
    private static Console instance;

    public static Console getInstance() {
        if (instance == null) {
            instance = new Console();
        }
        return instance;
    }

    public void print(String s) {
        System.out.print(s);
    }

    public void println(String s) {
        System.out.println(s);
    }

    public String readLine() {
        return new Scanner(System.in).nextLine();
    }
}
