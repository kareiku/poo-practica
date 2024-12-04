package org.example.models;

public enum Category {
    POINTS_SCORED("Puntos marcados"),
    MATCHES_WON("Partidos ganados"),
    ASSIST_POINTS("Puntos de asistencia"),
    TOURNAMENTS_WON("Torneos ganados en el pasado"),
    EARNINGS("Dinero generado en el torneo");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
