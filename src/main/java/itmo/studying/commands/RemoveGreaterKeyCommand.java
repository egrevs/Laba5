package itmo.studying.commands;

import itmo.studying.exceptions.CollectionIsEmptyException;
import itmo.studying.exceptions.WrongAmountOfElementsException;
import itmo.studying.utils.CollectionManager;
import itmo.studying.utils.Console;

public class RemoveGreaterKeyCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveGreaterKeyCommand(CollectionManager collectionManager) {
        super("remove_greater_key null", "удалить из коллекции все элементы, ключ которых превышает заданный");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long key = Long.parseLong(argument);
            collectionManager.removeGreaterKey(key);
            Console.println("Элементы успешно удалены!");
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Использование: " + getName() + "!");
        } catch (CollectionIsEmptyException e) {
            Console.printErr("Коллекция пуста!");
        }
        return false;
    }
}
