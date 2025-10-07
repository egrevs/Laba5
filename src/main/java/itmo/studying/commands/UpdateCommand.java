package itmo.studying.commands;

import itmo.studying.data.*;
import itmo.studying.exceptions.CollectionIsEmptyException;
import itmo.studying.exceptions.IncorrectInputInScriptException;
import itmo.studying.exceptions.WorkerNotFoundException;
import itmo.studying.exceptions.WrongAmountOfElementsException;
import itmo.studying.utils.CollectionManager;
import itmo.studying.utils.Console;
import itmo.studying.utils.WorkerRequester;

public class UpdateCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private WorkerRequester workerRequester;

    public UpdateCommand(CollectionManager collectionManager, WorkerRequester workerRequester) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.workerRequester = workerRequester;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Worker worker = collectionManager.getById(Long.parseLong(argument));
            if (worker == null) throw new WorkerNotFoundException();

            String name = worker.getName();
            Coordinates coordinates = worker.getCoordinates();
            Float salary = worker.getSalary();
            Status status = worker.getStatus();
            Organization organization = worker.getOrganization();
            Position position = worker.getPosition();

            if (workerRequester.askQuestion("Хотите изменить имя работника?")) name = workerRequester.askName();
            if (workerRequester.askQuestion("Хотите изменить координаты работника?"))
                coordinates = workerRequester.askCoordinates();
            if (workerRequester.askQuestion("Хотите изменить зарплату работника?"))
                salary = workerRequester.askSalary();
            if (workerRequester.askQuestion("Хотите изменить статус работника?")) status = workerRequester.askStatus();
            if (workerRequester.askQuestion("Хотите изменить организацию работника?"))
                organization = workerRequester.askOrganization();
            if (workerRequester.askQuestion("Хотите изменить позицию работника?"))
                position = workerRequester.askPosition();

            collectionManager.updateById(Long.parseLong(argument), new Worker(
                    name,
                    coordinates,
                    salary,
                    position,
                    status,
                    organization
            ));

            Console.println("Характеристики сотрудника успешно обновлены!");
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Использование: " + getName() + "!");
        } catch (WorkerNotFoundException e) {
            Console.printError("Работник не найден!");
        } catch (CollectionIsEmptyException e) {
            Console.printError("Коллекция пуста!");
        } catch (IncorrectInputInScriptException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
