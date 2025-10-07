package itmo.studying.commands;

import itmo.studying.exceptions.WorkerNotFoundException;
import itmo.studying.exceptions.WrongAmountOfElementsException;
import itmo.studying.utils.CollectionManager;
import itmo.studying.utils.Console;

public class RemoveByKeyCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveByKeyCommand(CollectionManager collectionManager) {
        super("remove_key null", "удалить элемент из коллекции по его ключу");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            Long key = Long.parseLong(argument);
            if (collectionManager.getById(key) == null) throw new WorkerNotFoundException();
            collectionManager.removeByKey(key);
            Console.println("Сотрудник успешно удален!");
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Использование: " + getName() + "!");
        } catch (WorkerNotFoundException e) {
            Console.printErr("Работник с таким ключем не найден!");
        }
        return false;
    }
}
