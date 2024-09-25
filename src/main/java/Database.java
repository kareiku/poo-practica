import java.util.TreeMap;

public class Database {
    private final TreeMap<String, Player> database;

    public Database() {
        this.database = new TreeMap<>();
    }

    public void create(Player player) {
        if (!this.database.containsValue(player)) {
            this.database.put(player.getName(), player);
        }
    }

    public void remove(Player player) {
        this.database.remove(player.getName());
    }

    public void show() {
        for (Player player : this.database.values()) {
            System.out.println("- " + player);
        }
    }

    public void rank() {
        this.show();
    }

    public void score(Player player, double score) {
        if (this.database.containsValue(player)) {
            this.database.remove(player.getName());
            player.setScore(score);
            this.database.put(player.getName(), player);
        }
    }

    public void showMatchmake() {
    }

    public void clearMatchmake() {
    }

    public void matchmake(Player player1, Player player2) {
    }

    public void randomMatchmake() {
    }

    private Player randomPlayerFromList() {
        Player[] values = this.database.values().toArray(new Player[0]);
        return values[((int) (Math.random() * values.length))];
    }
}
