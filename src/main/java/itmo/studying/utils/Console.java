package itmo.studying.utils;

public class Console {
    public static void println(String argument) {
        System.out.println(argument);
    }

    public static void print(String argument) {
        System.out.print(argument);
    }

    public static void printErr(String argument) {
        System.out.println("error: " + argument);
    }
}
