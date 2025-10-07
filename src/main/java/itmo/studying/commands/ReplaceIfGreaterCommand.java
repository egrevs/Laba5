package itmo.studying.commands;

import itmo.studying.data.Worker;
import itmo.studying.exceptions.CollectionIsEmptyException;
import itmo.studying.exceptions.IncorrectInputInScriptException;
import itmo.studying.exceptions.WorkerNotFoundException;
import itmo.studying.exceptions.WrongAmountOfElementsException;
import itmo.studying.utils.CollectionManager;
import itmo.studying.utils.Console;
import itmo.studying.utils.WorkerRequester;

public class ReplaceIfGreaterCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private WorkerRequester workerRequester;

    public ReplaceIfGreaterCommand(CollectionManager collectionManager, WorkerRequester workerRequester) {
        super("replace_if_greater null {element}", "заменить значение по ключу, если новое значение больше старого");
        this.collectionManager = collectionManager;
        this.workerRequester = workerRequester;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long key = Long.parseLong(argument);
            Worker worker = collectionManager.getById(key);
            if (worker == null) throw new WorkerNotFoundException();

            collectionManager.replaceIfGreater(key, new Worker(
                    workerRequester.askName(),
                    workerRequester.askCoordinates(),
                    workerRequester.askSalary(),
                    workerRequester.askPosition(),
                    workerRequester.askStatus(),
                    workerRequester.askOrganization()
            ));

        } catch (WrongAmountOfElementsException e) {
            Console.println("Использование: " + getName() + "!");
        } catch (IncorrectInputInScriptException e) {
            throw new RuntimeException(e);
        } catch (CollectionIsEmptyException e) {
            Console.printError("Коллекция пуста!");
        } catch (WorkerNotFoundException e) {
            Console.printError("Работник с таким ключом не найден!");
        }
        return false;
    }
}
