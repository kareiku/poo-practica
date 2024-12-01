package org.example.models;

public enum Category {
    POINTS_SCORED("Puntos marcados"),
    MATCHES_WON("Partidos ganados"),
    ASSISTANCE_POINTS("Puntos de asistencia"),
    PREVIOUSLY_WON_TOURNAMENTS("Torneos ganados en el pasado"),
    TOURNAMENT_EARNINGS("Dinero generado en el torneo");

    public final String name;

    Category(String name) {
        this.name = name;
    }
}
