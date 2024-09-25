public class Player {
    private final String name;
    private double score;

    public Player(String name) {
        this.name = name;
        this.score = 0.0;
    }

    public String toString() {
        return this.name + ": " + this.score;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score >= -999999.0 ? score : 0.0;
    }
}
