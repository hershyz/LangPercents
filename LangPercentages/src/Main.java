import java.util.Scanner;

public class Main {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("dir> ");
        String dir = scn.nextLine();

        Engine engine = new Engine(dir);
        engine.run();
    }
}