package org.example.models;

public interface Participant {
    String getStatisticsFormat(String option);

    boolean matches(String identifier);
}
