public class Player {
    private final String name;
    private double score;
    private Player match;

    public Player(String name) {
        this.name = name;
        score = 0.0;
        match = null;
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

    public Player getMatch() {
        return match;
    }

    public void setMatch(Player match) {
        this.match = match;
    }

    public String toString() {
        return this.name + ": " + this.score;
    }
}
