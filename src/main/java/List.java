import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class List {
    private static final int INIT_VALUE = 10;
    private Player[] players;
    private int count = 0;
    private final ArrayList<Matchmake> matchmakes;

    public List() {
        this.players = new Player[INIT_VALUE];
        this.matchmakes = new ArrayList<>();
    }

    public void create(Player player) {
        if (!this.contains(player)) {
            if (count >= INIT_VALUE) {
                Player[] temp = players;
                players = new Player[count * 2];
                System.arraycopy(temp, 0, players, 0, temp.length);
            }

            players[count] = player;
            count++;
        }
    }

    public void remove(Player player) {
        if (this.contains(player)) {
            for (int i = this.getIndex(player); i < players.length - 1; i++) {
                players[i] = players[i + 1];
            }
        }
    }

    public void show() {
        for (Player player : this.players) {
            System.out.println("- " + player);
        }
    }

    public void rank() {
        this.sort();
        this.show();
    }

    public void score(Player player, double score) {
        if (this.contains(player)) {
            players[getIndex(player)].setScore(score);
        }
    }

    public void showMatchmake() {
    }

    public void clearMatchmake() {
    }

    public void matchmake(String name1, String name2) {
        if (name1 != null && name2 != null) {
            Player player1 = this.getPlayer(name1);
            Player player2 = this.getPlayer(name2);

            if (player1 != null && player2 != null) {
                matchmakes.add(new Matchmake(player1, player2));
            }
        }
    }

    public void randomMatchmake() {
        if (count % 2 == 0) {
            Player[] temp = Arrays.copyOf(players, players.length);
            ArrayDeque<Player> queue = new ArrayDeque<>();

            while (!this.isEmpty()) {
                Player aux = this.randomPlayerFromList();
                queue.offer(aux);
                this.remove(aux);
            }

            players = temp;

            while (!queue.isEmpty()) {
                this.matchmake(queue.pop().getName(), queue.pop().getName());
            }
        }
    }

    private boolean isEmpty() {
        return count == 0;
    }

    private boolean contains(Player player) {
        boolean contained;
        int i = 0;

        do {
            contained = player == players[i];
            i++;
        } while (!contained && i < players.length);

        return contained;
    }

    private int getIndex(Player player) {
        int index = -1;
        int i = 0;

        while (index == -1 && i < players.length) {
            if (players[i] == player) {
                index = i;
            }
            i++;
        }

        return index;
    }

    private Player getPlayer(String name) {
        Player ret = null;

        for (Player player : players) {
            if (player.getName().equals(name)) {
                ret = player;
            }
        }

        return ret;
    }

    private void sort() {
        int pos = 0;
        while (pos < players.length) {
            if (pos == 0 || players[pos].getScore() <= players[pos - 1].getScore()) {
                pos++;
            } else {
                Player temp = players[pos];
                players[pos] = players[pos - 1];
                players[pos - 1] = temp;
            }
        }
    }

    private Player randomPlayerFromList() {
        Player temp = this.players[((int) (Math.random() * players.length))];
        return new Player(temp.getName(), temp.getScore());
    }
}
