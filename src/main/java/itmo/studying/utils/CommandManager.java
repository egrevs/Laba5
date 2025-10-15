package itmo.studying.utils;

import itmo.studying.commands.*;
import itmo.studying.exceptions.CommandHistoryIsEmpty;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final int COMMAND_HISTORY_SIZE = 6;

    private List<Command> commandList = new ArrayList<>();
    private String[] commandHistory = new String[COMMAND_HISTORY_SIZE];

    private ClearCommand clearCommand;
    private CountLessThanSalaryCommand countLessThanSalaryCommand;
    private ExecuteScriptCommand executeScriptCommand;
    private ExitCommand exitCommand;
    private GroupCountingByNameCommand groupCountingByNameCommand;
    private HelpCommand helpCommand;
    private HistoryCommand historyCommand;
    private InfoCommand infoCommand;
    private InsertCommand insertCommand;
    private RemoveAllByStatusCommand removeAllByStatusCommand;
    private RemoveByKeyCommand removeByKeyCommand;
    private RemoveGreaterKeyCommand removeGreaterKeyCommand;
    private ReplaceIfGreaterCommand replaceIfGreaterCommand;
    private SaveCommand saveCommand;
    private ShowCommand showCommand;
    private UpdateCommand updateCommand;

    public CommandManager(ClearCommand clearCommand, CountLessThanSalaryCommand countLessThanSalaryCommand,
                          ExecuteScriptCommand executeScriptCommand, ExitCommand exitCommand,
                          GroupCountingByNameCommand groupCountingByNameCommand, HelpCommand helpCommand,
                          HistoryCommand historyCommand, InfoCommand infoCommand, InsertCommand insertCommand,
                          RemoveAllByStatusCommand removeAllByStatusCommand, RemoveByKeyCommand removeByKeyCommand,
                          RemoveGreaterKeyCommand removeGreaterKeyCommand,
                          ReplaceIfGreaterCommand replaceIfGreaterCommand, SaveCommand saveCommand,
                          ShowCommand showCommand, UpdateCommand updateCommand) {
        this.clearCommand = clearCommand;
        this.countLessThanSalaryCommand = countLessThanSalaryCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.groupCountingByNameCommand = groupCountingByNameCommand;
        this.helpCommand = helpCommand;
        this.historyCommand = historyCommand;
        this.infoCommand = infoCommand;
        this.insertCommand = insertCommand;
        this.removeAllByStatusCommand = removeAllByStatusCommand;
        this.removeByKeyCommand = removeByKeyCommand;
        this.removeGreaterKeyCommand = removeGreaterKeyCommand;
        this.replaceIfGreaterCommand = replaceIfGreaterCommand;
        this.saveCommand = saveCommand;
        this.showCommand = showCommand;
        this.updateCommand = updateCommand;

        commandList.add(clearCommand);
        commandList.add(countLessThanSalaryCommand);
        commandList.add(executeScriptCommand);
        commandList.add(exitCommand);
        commandList.add(groupCountingByNameCommand);
        commandList.add(helpCommand);
        commandList.add(historyCommand);
        commandList.add(infoCommand);
        commandList.add(insertCommand);
        commandList.add(removeAllByStatusCommand);
        commandList.add(removeByKeyCommand);
        commandList.add(removeGreaterKeyCommand);
        commandList.add(replaceIfGreaterCommand);
        commandList.add(saveCommand);
        commandList.add(showCommand);
        commandList.add(updateCommand);
    }

    public void addToHistory(String commandToAdd) {
        for (Command command : commandList) {
            if (command.getName().equals(commandToAdd)) {
                for (int i = COMMAND_HISTORY_SIZE - 1; i > 0; i--) {
                    commandHistory[i] = commandHistory[i - 1];
                }
                commandHistory[0] = commandToAdd;
            }
        }
    }

    public boolean history(String argument) {
        if (!helpCommand.execute(argument)) {
            try {
                if (commandHistory.length == 0) throw new CommandHistoryIsEmpty();
                Console.println("Список последних используемых команд: ");
                for (String s : commandHistory) {
                    if (s != null) Console.print(s + ", ");
                }
                return true;
            } catch (CommandHistoryIsEmpty e) {
                Console.printError("Ни одна команда не была использована!");
            }
        }
        return false;
    }

    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }

    public boolean countLessThanSalary(String argument) {
        return countLessThanSalaryCommand.execute(argument);
    }

    public boolean executeScript(String argument) {
        return executeScriptCommand.execute(argument);
    }

    public boolean exit(String argument) {
        return exitCommand.execute(argument);
    }

    public boolean groupCountingByName(String argument) {
        return groupCountingByNameCommand.execute(argument);
    }

    public boolean help(String argument) {
        commandList.forEach(command -> Console.println(command.getName() + " " + command.getDescription()));
        return helpCommand.execute(argument);
    }

    public boolean info(String argument) {
        return infoCommand.execute(argument);
    }

    public boolean insert(String argument) {
        return insertCommand.execute(argument);
    }

    public boolean removeAllByStatus(String argument) {
        return removeAllByStatusCommand.execute(argument);
    }

    public boolean removeByKey(String argument) {
        return removeByKeyCommand.execute(argument);
    }

    public boolean removeGreaterKey(String argument) {
        return removeGreaterKeyCommand.execute(argument);
    }

    public boolean replaceIfGreater(String argument) {
        return replaceIfGreaterCommand.execute(argument);
    }

    public boolean save(String argument) {
        return saveCommand.execute(argument);
    }

    public boolean show(String argument) {
        return showCommand.execute(argument);
    }

    public boolean update(String argument) {
        return updateCommand.execute(argument);
    }

    public boolean noSuchCommand(String command) {
        Console.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;
    }

    @Override
    public String toString() {
        return "CommandManager (класс-обертка для работы с командами)";
    }
}
