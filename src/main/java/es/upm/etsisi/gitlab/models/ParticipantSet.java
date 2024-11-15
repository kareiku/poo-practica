package es.upm.etsisi.gitlab.models;

import java.util.HashSet;

public class ParticipantSet extends HashSet<Participant> {
    public ParticipantSet() {
    }

    public ParticipantSet(HashSet<Participant> set) {
        super(set);
    }

    @Deprecated
    public void create(String[] args) {
        assert args.length > 1;

        String name = args[1];
        if (!this.contains(this.get(name)))
            this.add(new Player((name)));
    }

    @Deprecated
    public void remove(String[] args) {
        assert args.length > 1;

        this.remove(this.get(args[1]));
    }

    @Deprecated
    public void rank() {
        this.show(this.sorted());
    }

    @Deprecated
    public void score(String[] args) {
        assert args.length > 1;

        args = args[1].split(";");

        assert args.length > 1;

        Player player = this.get(args[0]);
        double score = Double.parseDouble(args[1]);

        this.score(player, score);
    }

    /*
     * Uses the Gnome sort sorting algorithm (https://en.wikipedia.org/wiki/Gnome_sort)
     * to generate and return a sorted version of the players list.
     */
    @Deprecated
    private List<Player> sorted() {
        List<Player> temp = new ParticipantSet(this);

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

    public String toString() {
        StringBuilder format = new StringBuilder("DNI\tSurname\tName\n");
        for (Participant participant : this) {
            format.append(participant).append("\n");
        }
        return format.toString();
    }
}
