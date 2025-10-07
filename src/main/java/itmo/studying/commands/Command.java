package itmo.studying.commands;

public interface Command {
    String getName();

    String getDescription();

    boolean execute(String argument);
}
