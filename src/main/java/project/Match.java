package project;

public class Match {
    private final Player home;
    private final Player guest;

    public Match(Player home, Player guest) {
        assert home != null;
        assert guest != null;

        this.home = home;
        this.guest = guest;
    }

    public Player getHome() {
        return home;
    }

    public Player getGuest() {
        return guest;
    }

    public String toString() {
        return home + "vs. " + guest;
    }
}
