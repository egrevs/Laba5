package itmo.studying.utils;

public class Console {
    public static void println(String argument) {
        System.out.println(argument);
    }

    public static void print(String argument) {
        System.out.print(argument);
    }

    public static void printError(String argument) {
        System.out.println("error: " + argument);
    }
}
