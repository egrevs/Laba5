package itmo.studying.data;

public enum Position {
    HEAD_OF_DEPARTMENT,
    BAKER,
    CLEANER;

    public static String nameList() {
        String nameList = "";
        for (Position position : Position.values()) {
            nameList += position.name() + ", ";
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}