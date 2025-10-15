package itmo.studying.commands;

/**
 * Команда вывода всех элементов коллекции в строковом представлении.
 */

import itmo.studying.data.Worker;
import itmo.studying.exceptions.WrongAmountOfElementsException;
import itmo.studying.utils.CollectionManager;
import itmo.studying.utils.Console;

public class ShowCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все" +
                " элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.getHashMap().isEmpty()) {
                Console.println("Коллекция пуста");
                return true;
            }
            for (Worker worker : collectionManager.getHashMap().values()) {
                Console.println(worker);
            }
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Использование: " + getName() + "!");
        }
        return false;
    }
}
