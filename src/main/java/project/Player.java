package project;

public class Player {
    private final String name;
    private double score;
    private static final double DEFAULT_SCORE = 0.0;
    private static final double MIN_SCORE = -999999.0;

    public Player(String name) {
        assert name != null;

        this.name = name;
        score = DEFAULT_SCORE;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        assert score >= MIN_SCORE;

        this.score = score;
    }

    public String toString() {
        return String.format("%-25s %-6.1f", name, score);
    }
}
