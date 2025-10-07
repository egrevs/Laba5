package itmo.studying.commands;

import itmo.studying.exceptions.WrongAmountOfElementsException;
import itmo.studying.utils.CollectionManager;
import itmo.studying.utils.Console;

import java.time.LocalDateTime;

public class InfoCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            LocalDateTime lastInitTime = collectionManager.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                    lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

            LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                    lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

            Console.println("Информация о коллекции: ");
            Console.println("Тип коллекции: " + collectionManager.getCollectionType());
            Console.println("Количество элементов коллекции: " + collectionManager.collectionSize());
            Console.println(" Дата последнего сохранения: " + lastSaveTimeString);
            Console.println(" Дата последней инициализации: " + lastInitTimeString);

            return true;
        } catch (WrongAmountOfElementsException e){
            Console.println("Использование: " + getName() + "!");
        }
        return false;
    }
}
