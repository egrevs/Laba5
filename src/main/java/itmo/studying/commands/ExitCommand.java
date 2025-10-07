package itmo.studying.commands;

import itmo.studying.exceptions.WrongAmountOfElementsException;
import itmo.studying.utils.Console;

public class ExitCommand extends AbstractCommand{

    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.println("Завершаю программу...");
            System.exit(0);
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Использование: " + getName() + "!");
        }
        return false;
    }
}
