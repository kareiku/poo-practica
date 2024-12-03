package org.example.models;

public class Player extends Participant {
    private final String surname;
    private final String DNI;

    public Player(String name, String surname, String DNI) {
        super(name);
        this.surname = surname;
        this.DNI = DNI;
    }

    public void rate(Category category, double score) {
        this.getStatistics().replace(category, score);
    }

    public boolean equals(Player player) {
        return this.DNI.equals(player.DNI);
    }

    public String statisticsFormat(String option) {
        if (option.equals("-csv")) {
            return this.statisticsCSV();
        } else if (option.equals("-json")) {
            return this.statisticsJSON();
        } else {
            return this.statisticsDefault();
        }
    }

    private String statisticsDefault() {
        StringBuilder format = new StringBuilder();
        this.getStatistics().forEach((key, value) -> format
                .append("Player with DNI ")
                .append(this.DNI)
                .append(" has a score of ")
                .append(value)
                .append(" in the category ")
                .append(key.name)
                .append(".\n"));
        return format.toString();
    }

    private String statisticsCSV() {
        StringBuilder format = new StringBuilder();
        Double[] ratings = this.getStatistics().values().toArray(new Double[0]);
        for (int i = 0; i < ratings.length - 1; i++) {
            format.append(ratings[i]).append(',');
        }
        format.append(ratings[ratings.length - 1]);
        return format.toString();
    }

    private String statisticsJSON() {
        StringBuilder format = new StringBuilder();
        Category[] categories = this.getStatistics().keySet().toArray(new Category[0]);
        Double[] scores = this.getStatistics().values().toArray(new Double[0]);
        format.append('{').append('\n');
        for (int i = 0; i < categories.length - 1; i++) {
            format.append(String.format("\t\"%s\": %f,\n", categories[i].name, scores[i]));
        }
        format.append(String.format("\t\"%s\": %f\n", categories[categories.length - 1].name, scores[categories.length]));
        format.append('}').append('\n');
        return format.toString();
    }
}
