package project;

public class Match {
    private Player home;
    private Player guest;

    public Match(Player home, Player guest) {
        assert home != null && guest != null;
        this.home = home;
        this.guest = guest;
    }

    public Player getHome() {
        return home;
    }

    public void setHome(Player home) {
        assert home != null;
        this.home = home;
    }

    public Player getGuest() {
        return guest;
    }

    public void setGuest(Player guest) {
        assert guest != null;
        this.guest = guest;
    }
}
