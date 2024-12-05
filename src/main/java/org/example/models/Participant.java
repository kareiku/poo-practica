package org.example.models;

public interface Participant {
    String getIdentifier();

    String getStatsFormat(String option);

    double rating();
}
