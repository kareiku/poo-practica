package project;

public class SportManagementSystem {
    private final static String EXIT_CMD = "exit";

    public static void main(String[] args) {
        Manager manager = new Manager();

        manager.input("create Luisa");
        manager.input("score Luisa 4.5");

        manager.input("create Manuel");
        manager.input("score Manuel 2.7");

        manager.input("create Kurt");
        manager.input("score Kurt 4.0");

        manager.input("create Sofia");
        manager.input("score Sofia 3.8");

        manager.input("create Robert");
        manager.input("score Robert 3.8");

        while (!manager.read().equals(EXIT_CMD)) ;
    }
}
