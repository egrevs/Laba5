package itmo.studying.commands;

import itmo.studying.exceptions.CollectionIsEmptyException;
import itmo.studying.exceptions.WrongAmountOfElementsException;
import itmo.studying.utils.CollectionManager;
import itmo.studying.utils.Console;

public class ClearCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            collectionManager.clear();
            Console.println("Коллекция успешно очищена!");
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.printError("Использование: " + getName() + "!");
        } catch (CollectionIsEmptyException e) {
            Console.println("В коллекции итак нет элементов!");
        }
        return false;
    }
}
