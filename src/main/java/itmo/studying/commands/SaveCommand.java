package itmo.studying.commands;

import itmo.studying.exceptions.WrongAmountOfElementsException;
import itmo.studying.utils.CollectionManager;
import itmo.studying.utils.Console;

public class SaveCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.save();
            Console.println("Коллекция успешно сохранена в файл!");
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Использование: " + getName() + "!");
        }
        return false;
    }
}
