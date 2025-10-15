package itmo.studying.utils;

import itmo.studying.exceptions.IllegalStateException;
import itmo.studying.exceptions.ScriptRecursionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console {
    private CommandManager commandManager;
    private Scanner userScanner;
    private List<String> scripts = new ArrayList<>();
    private WorkerRequester workerRequester;

    public Console(CommandManager commandManager, Scanner userScanner, WorkerRequester workerRequester) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
        this.workerRequester = workerRequester;
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

    public int scriptMode(String argument) {
        String[] commands;
        int commandStatus = 0;
        scripts.add(argument);
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = workerRequester.getUserScanner();
            workerRequester.setUserScanner(tmpScanner);
            workerRequester.setFileMode();
            do {
                commands = (userScanner.nextLine().trim() + " ").split(" ", 2);
                commands[1] = commands[1].trim();
                while (scriptScanner.hasNextLine() && commands[0].isEmpty()) {
                    commands = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    commands[1] = commands[1].trim();
                }
                println("> " + String.join(" ", commands));
                if (commands[0].equals("execute_script")) {
                    for (String script : scripts) {
                        if (commands[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(commands);
            } while (commandStatus == 0 && scriptScanner.hasNextLine());
            workerRequester.setUserScanner(tmpScanner);
            workerRequester.setUserMode();

            if (commandStatus == 1 && !(commands[0].equals("execute_script") && !commands[1].isEmpty()))
                println("Проверьте скрипт на корректность входных данных!");

            return commandStatus;
        } catch (FileNotFoundException exception) {
            Console.printError("Файл не найден");
        } catch (NoSuchElementException exception) {
            Console.printError("Исполняемый файл пуст!");
        } catch (ScriptRecursionException exception) {
            Console.printError("Скрипт не может быть рекурсивным!");
        } catch (IllegalStateException exception) {
            Console.printError("Непредвиденная ошибка!");
            System.exit(0);
        } finally {
            scripts.remove(scripts.size() - 1);
        }
        return 1;
    }

    public static void println(Object argument) {
        System.out.println(argument);
    }

    public static void print(Object argument) {
        System.out.print(argument);
    }

    public static void printError(Object argument) {
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
            case "insert":
                if (!commandManager.insert(userCommand[1]))
                    return 1;
            default:
                if (!commandManager.noSuchCommand(userCommand[1]))
                    return 1;
        }
        return 0;
    }
}
