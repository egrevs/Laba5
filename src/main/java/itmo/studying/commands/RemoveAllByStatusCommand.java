package itmo.studying.commands;

import itmo.studying.data.Status;
import itmo.studying.exceptions.CollectionIsEmptyException;
import itmo.studying.exceptions.WrongAmountOfElementsException;
import itmo.studying.utils.CollectionManager;
import itmo.studying.utils.Console;

public class RemoveAllByStatusCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveAllByStatusCommand(CollectionManager collectionManager) {
        super("remove_all_by_status status",
                "удалить из коллекции все элементы, значение поля status которого эквивалентно заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            collectionManager.removeAllByStatus(Status.valueOf(argument));
            Console.println("Элементы успешно удалены!");
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Использование: " + getName() + "!");
        } catch (CollectionIsEmptyException e) {
            Console.printError("Коллекция пуста!");
        }
        return false;
    }
}
