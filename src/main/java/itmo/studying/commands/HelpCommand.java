package itmo.studying.commands;

/**
 * Команда отображения списка доступных команд и их описаний.
 */

import itmo.studying.exceptions.WrongAmountOfElementsException;
import itmo.studying.utils.Console;

public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        super("help", "вывести справку по доступным командам");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Использование: " + getName() + "!");
        }
        return false;
    }
}
