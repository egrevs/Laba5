package itmo.studying.commands;

import itmo.studying.data.Worker;
import itmo.studying.exceptions.IncorrectInputInScriptException;
import itmo.studying.exceptions.WrongAmountOfElementsException;
import itmo.studying.utils.CollectionManager;
import itmo.studying.utils.Console;
import itmo.studying.utils.WorkerRequester;

public class InsertCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private WorkerRequester workerRequester;

    public InsertCommand(CollectionManager collectionManager, WorkerRequester workerRequester) {
        super("insert null {element}", "добавить новый элемент с заданным ключом");
        this.collectionManager = collectionManager;
        this.workerRequester = workerRequester;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();

            collectionManager.insertByKey(Long.parseLong(argument), new Worker(
                    workerRequester.askName(),
                    workerRequester.askCoordinates(),
                    workerRequester.askSalary(),
                    workerRequester.askPosition(),
                    workerRequester.askStatus(),
                    workerRequester.askOrganization()
            ));

            Console.println("Элемент успешно добавлен в коллекцию!");
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Использование: " + getName() + "!");
        } catch (IncorrectInputInScriptException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
