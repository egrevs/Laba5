package itmo.studying.commands;

/**
 * Контракт команды: каждая команда имеет имя, описание и метод исполнения.
 */

public interface Command {
    String getName();

    String getDescription();

    boolean execute(String argument);
}
