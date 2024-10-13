package project.manager;

public class Match {
    private final Player home;
    private final Player guest;

    public Match(Player home, Player guest) {
        assert home != null;
        assert guest != null;

        this.home = home;
        this.guest = guest;
    }

    public boolean isComposedBy(Player player) {
        return player.equals(this.home) || player.equals(this.guest);
    }

    public String toString() {
        return home.getName() + " vs. " + guest.getName();
    }
}
