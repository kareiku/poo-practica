package project.utils;

import project.manager.Iterator;
import project.manager.LinkedList;
import project.manager.Player;

public enum PlayerList {
    PLAYERS;

    private final LinkedList<Player> players;

    PlayerList() {
        this.players = new LinkedList<>();
    }

    public void create(String[] args) {
        assert args.length > 1;

        String name = args[1];
        if (!this.players.contains(this.get(name)))
            this.players.add(new Player((name)));
    }

    public void remove(String[] args) {
        assert args.length > 1;

        players.remove(this.get(args[1]));
    }

    public void show() {
        this.show(this.players);
    }

    private void show(LinkedList<Player> players) {
        System.out.printf("%-25s %s\n", "Player", "Score");

        Iterator<Player> iterator = players.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void rank() {
        this.show(this.sorted());
    }

    public void score(String[] args) {
        assert args.length > 1;

        args = args[1].split(";");

        assert args.length > 1;

        Player player = this.get(args[0]);
        double score = Double.parseDouble(args[1]);

        this.score(player, score);
    }

    private void score(Player player, double score) {
        assert player != null;

        player.setScore(score);
    }

    Player get(String name) {
        Iterator<Player> iterator = players.listIterator();
        Player player = null;

        while (iterator.hasNext()) {
            Player aux = iterator.next();
            if (name.equals(aux.getName()))
                player = aux;
        }

        return player;
    }

    private LinkedList<Player> sorted() {
        LinkedList<Player> temp = new LinkedList<>(players);

        int index = 0;
        int size = temp.size();

        while (index < size) {
            if (index == 0 || temp.get(index).getScore() <= temp.get(index - 1).getScore()) {
                index++;
            } else {
                Player aux = temp.get(index);
                temp.set(index, temp.get(index - 1));
                temp.set(index - 1, aux);
                index--;
            }
        }

        return temp;
    }

    LinkedList<Player> copy() {
        return new LinkedList<>(players);
    }

    Iterator<Player> listIterator() {
        return players.listIterator();
    }
}
