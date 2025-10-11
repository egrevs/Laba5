package itmo.studying.utils;

import itmo.studying.exceptions.IllegalStateException;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console {
    private CommandManager commandManager;
    private Scanner userScanner;

    public Console(CommandManager commandManager, Scanner userScanner) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
    }

    public void interactiveMode() {
        String[] commands = {"", ""};
        int commandStatus;
        try {
            do {
                Console.println("$");
                commands = (userScanner.nextLine().trim() + " ").split(" ", 2);
                commands[1] = commands[1].trim();
                commandManager.addToHistory(commands[0]);
                commandStatus = launchCommand(commands);
            } while (commandStatus != 2);
        } catch (NoSuchElementException exception) {
            Console.printError("Пользовательский ввод не обнаружен!");
        } catch (IllegalStateException e) {
            Console.printError("Непредвиденная ошибка!");
            System.exit(0);
        }
    }

    public static void println(String argument) {
        System.out.println(argument);
    }

    public static void print(String argument) {
        System.out.print(argument);
    }

    public static void printError(String argument) {
        System.out.println("error: " + argument);
    }

    private int launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "clear":
                if (!commandManager.clear(userCommand[1]))
                    return 1;
                break;
            case "countLessThanSalary":
                if (!commandManager.countLessThanSalary(userCommand[1]))
                    return 1;
                break;
            case "executeScript":
                if (!commandManager.executeScript(userCommand[1]))
                    return 1;
                break;
            case "exit":
                if (!commandManager.exit(userCommand[1]))
                    return 1;
                break;
            case "groupCountingByName":
                if (!commandManager.groupCountingByName(userCommand[1]))
                    return 1;
                break;
            case "help":
                if (!commandManager.help(userCommand[1]))
                    return 1;
                break;
            case "history":
                if (!commandManager.history(userCommand[1]))
                    return 1;
                break;
            case "info":
                if (!commandManager.info(userCommand[1]))
                    return 1;
                break;
            case "removeAllByStatus":
                if (!commandManager.removeAllByStatus(userCommand[1]))
                    return 1;
                break;
            case "removeByKey":
                if (!commandManager.removeByKey(userCommand[1]))
                    return 1;
                break;
            case "removeGreaterKey":
                if (!commandManager.removeGreaterKey(userCommand[1]))
                    return 1;
                break;
            case "replaceIfGreater":
                if (!commandManager.replaceIfGreater(userCommand[1]))
                    return 1;
                break;
            case "save":
                if (!commandManager.save(userCommand[1]))
                    return 1;
                break;
            case "show":
                if (!commandManager.show(userCommand[1]))
                    return 1;
                break;
            case "update":
                if (!commandManager.update(userCommand[1]))
                    return 1;
                break;
            default:
                if (!commandManager.noSuchCommand(userCommand[1]))
                    return 1;
        }
        return 0;
    }
}
