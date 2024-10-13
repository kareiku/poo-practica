package project.manager;

public class SportManagementSystem {
    private final static String EXIT_CMD = "exit";

    private void start() {
        Decoder decoder = new Decoder();

        System.out.println(Message.WELCOME);
        System.out.println(Message.EXIT_WITH + EXIT_CMD);

        this.initialState(decoder);

        do {
            System.out.print(Message.INPUT);
        } while (!decoder.read().equals(EXIT_CMD));
    }

    private void initialState(Decoder decoder) {
        decoder.input("create Luisa");
        decoder.input("score Luisa;4.5");

        decoder.input("create Manuel");
        decoder.input("score Manuel;2.7");

        decoder.input("create Kurt");
        decoder.input("score Kurt;4.0");

        decoder.input("create Sofia");
        decoder.input("score Sofia;3.8");

        decoder.input("create Robert");
        decoder.input("score Robert;3.8");
    }

    public static void main(String[] args) {
        new SportManagementSystem().start();
    }
}
