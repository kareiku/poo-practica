public class DB {
    private static final int INIT_VALUE = 10;
    private Player[] list;
    private int count = 0;

    public DB() {
        this.list = new Player[INIT_VALUE];
    }

    public void create(Player player) {
        if (!this.contains(player)) {
            if (count >= INIT_VALUE) {
                Player[] temp = list;
                list = new Player[count * 2];
                for (Player e : temp) {
                }
            }
            list[count] = player;
            count++;
        }
    }

    public void remove(Player player) {
        if (this.contains(player)) {
            for (int i = this.getIndex(player); i < list.length - 1; i++) {
                list[i] = list[i + 1];
            }
        }
    }

    public void show() {
        for (Player player : this.list) {
            System.out.println("- " + player);
        }
    }

    public void rank() {
        this.show();
    }

    public void score(Player player, double score) {
        if (this.contains(player)) {
            list[getIndex(player)].setScore(score);
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

    private boolean contains(Player player) {
        boolean contained;
        int i = 0;
        do {
            contained = player == list[i];
            i++;
        } while (!contained && i < list.length);
        return contained;
    }

    private int getIndex(Player player) {
        int index = -1;
        int i = 0;
        while (index == -1 && i < list.length) {
            if (list[i] == player) {
                index = i;
            }
            i++;
        }
        return index;
    }

    private Player randomPlayerFromList() {
        return this.list[((int) (Math.random() * list.length))];
    }
}
