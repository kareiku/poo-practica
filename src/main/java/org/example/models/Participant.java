package org.example.models;

public interface Participant {
    String statisticsFormat(String option);

    boolean matches(String identifier);
}
