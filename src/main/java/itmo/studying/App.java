package itmo.studying;

/**
 * Точка входа приложения. Инициализирует инфраструктуру (файл, коллекцию,
 * запросчик ввода и набор команд) и запускает интерактивный режим консоли.
 */

import itmo.studying.commands.*;
import itmo.studying.utils.*;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try(Scanner scanner= new Scanner(System.in)){
            FileManager fileManager = new FileManager("LABA");
            CollectionManager collectionManager = new CollectionManager(fileManager);
            WorkerRequester workerRequester = new WorkerRequester(scanner);
            CommandManager commandManager = new CommandManager(
                    new ClearCommand(collectionManager),
                    new CountLessThanSalaryCommand(collectionManager),
                    new ExecuteScriptCommand(),
                    new ExitCommand(collectionManager),
                    new GroupCountingByNameCommand(collectionManager),
                    new HelpCommand(),
                    new HistoryCommand(),
                    new InfoCommand(collectionManager),
                    new InsertCommand(collectionManager, workerRequester),
                    new RemoveAllByStatusCommand(collectionManager),
                    new RemoveByKeyCommand(collectionManager),
                    new RemoveGreaterKeyCommand(collectionManager),
                    new ReplaceIfGreaterCommand(collectionManager, workerRequester),
                    new SaveCommand(collectionManager),
                    new ShowCommand(collectionManager),
                    new UpdateCommand(collectionManager, workerRequester)
            );

            Console console = new Console(commandManager, scanner, workerRequester);
            console.interactiveMode();
        }
    }
}
