package project;

public class SportManagementSystem {
    public static void main(String[] args) {
        Manager manager = new Manager();

        manager.input("run create Luisa");
        manager.input("run score Luisa 4.5");

        manager.input("run create Manuel");
        manager.input("run score Manuel 2.7");

        manager.input("run create Kurt");
        manager.input("run score Kurt 4.0");

        manager.input("run create Sofia");
        manager.input("run score Sofia 3.8");

        manager.input("run create Robert");
        manager.input("run score Robert 3.8");
    }
}
