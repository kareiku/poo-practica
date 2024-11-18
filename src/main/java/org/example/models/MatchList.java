package es.upm.etsisi.gitlab.models;

import java.util.LinkedList;
import java.util.Random;

public class MatchList extends LinkedList<Match> {
    public MatchList() {
    }

    public void add(Participant[] participants) {
        this.add(new Match(participants));
    }

    @Deprecated
    public String toString() {
        StringBuilder format = new StringBuilder("Current matches:\n");
        for (Match match : this) {
            format.append(match).append("\n");
        }
        return format.toString();
    }
}
