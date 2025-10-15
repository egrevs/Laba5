package itmo.studying.data;

/**
 * Модель организации работника: полное имя, годовой оборот и число сотрудников.
 */

import java.util.Objects;

public class Organization {
    private String fullName; //Поле не может быть null
    private float annualTurnover; //Значение поля должно быть больше 0
    private long employeesCount; //Значение поля должно быть больше 0

    public Organization(String fullName, float annualTurnover, long employeesCount) {
        this.fullName = fullName;
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public float getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(float annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public long getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(long employeesCount) {
        this.employeesCount = employeesCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;
        return Float.compare(annualTurnover, that.annualTurnover) == 0 && employeesCount == that.employeesCount && Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(fullName);
        result = 31 * result + Float.hashCode(annualTurnover);
        result = 31 * result + Long.hashCode(employeesCount);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "fullName='" + fullName + '\'' +
                ", annualTurnover=" + annualTurnover +
                ", employeesCount=" + employeesCount +
                '}';
    }
}
