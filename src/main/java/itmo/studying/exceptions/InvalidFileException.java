package itmo.studying.exceptions;

/**
 * Исключение, выбрасываемое при проблемах с файлом конфигурации/данных.
 */

public class InvalidFileException extends RuntimeException {
    public InvalidFileException(String message) {
        super(message);
    }
}
