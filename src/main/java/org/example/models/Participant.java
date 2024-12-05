package org.example.models;

public interface Participant {
    String getIdentifier();

    boolean matches(String identifier);
}
