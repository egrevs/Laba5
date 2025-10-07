package itmo.studying.commands;

import itmo.studying.data.Status;
import itmo.studying.exceptions.CollectionIsEmptyException;
import itmo.studying.exceptions.WrongAmountOfElementsException;
import itmo.studying.utils.CollectionManager;
import itmo.studying.utils.Console;

public class GroupCountingByNameCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public GroupCountingByNameCommand(CollectionManager collectionManager) {
        super("group_counting_by_name ",
                "сгруппировать элементы коллекции по значению поля name, вывести количество элементов в каждой группе");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            collectionManager.groupCountingName();
            Console.println("Группы успешно сформированы!");
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Использование: " + getName() + "!");
        } catch (CollectionIsEmptyException e) {
            Console.printErr("Коллекция пуста!");
        }
        return false;
    }
}
