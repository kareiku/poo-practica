import java.util.ArrayDeque;
import java.util.LinkedList;

public class Manager {
    private final LinkedList<Player> players;

    public Manager() {
        players = new LinkedList<>();
    }

    public void create(String name) {
        boolean isListed = false;
        int i = 0;

        while (!isListed && i < players.size()) {
            if (players.get(i).getName().equals(name)) {
                isListed = true;
            }
            i++;
        }

        if (!isListed) {
            players.offer(new Player(name));
        }
    }

    public void remove(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                players.remove(player);
            }
        }
    }

    public void show() {
        for (Player player : players) {
            System.out.println(player);
        }
    }

    public void rank() {
        this.sort();
        this.show();
    }

    private void sort() {
        ArrayDeque<Player> unsorted = new ArrayDeque<>();
        ArrayDeque<Player> sorted = new ArrayDeque<>();

        while (!players.isEmpty()) {
            unsorted.offer(players.pop());
        }

        Player min = unsorted.pop();
        while (!unsorted.isEmpty()) {
            Player temp = unsorted.pop();

            if (temp.getScore() < min.getScore()) {
                
            }
        }

        while (!sorted.isEmpty()) {
            players.offer(sorted.pop());
        }
    }

    public void score(Player player, double score) {
        player.setScore(score);
    }

    public void showMatchmake() {
        for (Player player : players) {
            this.showMatchmake(player);
        }
    }

    private void showMatchmake(Player player) {
        System.out.println(player.getName() + " is matched with " + player.getMatch().getName());
    }

    public void clearMatchmake() {
        for (Player player : players) {
            this.clearMatchmake(player);
        }
    }

    private void clearMatchmake(Player player) {
        player.setMatch(null);
    }

    public void matchmake(Player player1, Player player2) {
        player1.setMatch(player2);
        player2.setMatch(player1);
    }

    public void randomMatchmake() {
        Player player1 = this.randomPlayer();
        Player player2 = this.randomPlayer();
        this.matchmake(player1, player2);
    }

    private Player randomPlayer() {
        return players.get((int) (Math.random() * players.size()));
    }
}
