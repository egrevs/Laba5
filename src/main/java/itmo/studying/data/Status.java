package itmo.studying.data;

/**
 * Статус трудоустройства работника.
 */

public enum Status {
    FIRED,
    HIRED,
    REGULAR;

    public static String nameList() {
        String nameList = "";
        for (Status status : Status.values()) {
            nameList += status.name() + ", ";
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}
