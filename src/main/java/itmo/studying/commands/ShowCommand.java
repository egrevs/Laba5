package itmo.studying.commands;

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
            Console.println(String.valueOf(collectionManager));
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Использование: " + getName() + "!");
        }
        return false;
    }
}
