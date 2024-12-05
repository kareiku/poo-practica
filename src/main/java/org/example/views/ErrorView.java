package org.example.views;

import org.example.utils.Console;
import org.example.utils.Error;

import java.util.HashMap;
import java.util.Map;

public class ErrorView {
    private final Map<Error, String> errors;

    public ErrorView() {
        this.errors = new HashMap<>();
    }

    public void println(Error error) {
        Console.getInstance().println(errors.get(error));
    }
}
