package itmo.studying.commands;

import itmo.studying.exceptions.WrongAmountOfElementsException;
import itmo.studying.utils.Console;

public class HistoryCommand extends AbstractCommand{

    public HistoryCommand() {
        super("history", "вывести последние 6 команд (без их аргументов)");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())throw new WrongAmountOfElementsException();
        } catch (WrongAmountOfElementsException e) {
            Console.println("Использование: " + getName() + "!");
        }
        return false;
    }
}
