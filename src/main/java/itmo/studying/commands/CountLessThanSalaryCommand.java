package itmo.studying.commands;

import itmo.studying.exceptions.CollectionIsEmptyException;
import itmo.studying.exceptions.WrongAmountOfElementsException;
import itmo.studying.utils.CollectionManager;
import itmo.studying.utils.Console;

public class CountLessThanSalaryCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public CountLessThanSalaryCommand(CollectionManager collectionManager) {
        super("count_less_than_salary salary", "вывести количество элементов, значение поля salary которых меньше заданного");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Float salary = Float.parseFloat(argument);
            Console.println("Количество элементов - " + collectionManager.countLessThanSalary(salary));
            Console.println("Операция успешно выполнена!");
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Использование: " + getName() + "!");
        } catch (CollectionIsEmptyException e) {
            Console.printError("Коллекция пуста!");
        }
        return false;
    }
}
