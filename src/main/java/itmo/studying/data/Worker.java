package itmo.studying.data;

/**
 * Сущность работника коллекции. Имеет автоинкрементируемый идентификатор,
 * набор обязательных полей и сравнение по идентификатору.
 */

import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Worker implements Comparable<Worker> {
    private static final AtomicInteger integer = new AtomicInteger(0);

    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float salary; //Поле может быть null, Значение поля должно быть больше 0
    private Position position; //Поле не может быть null
    private Status status; //Поле не может быть null
    private Organization organization; //Поле может быть null

    public Worker(String name, Coordinates coordinates, Float salary, Position position, Status status,
                  Organization organization) {
        validateFields(name, coordinates, salary, position, status, organization);

        this.id = integer.incrementAndGet();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.salary = salary;
        this.position = position;
        this.status = status;
        this.organization = organization;
    }

    public Integer getId() {
        return id;
    }

    public static void resetIdCounter(int nextId) {
        integer.set(Math.max(0, nextId));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Worker worker = (Worker) o;
        return Objects.equals(id, worker.id) && Objects.equals(name, worker.name) && Objects.equals(coordinates, worker.coordinates) && Objects.equals(creationDate, worker.creationDate) && Objects.equals(salary, worker.salary) && position == worker.position && status == worker.status && Objects.equals(organization, worker.organization);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(coordinates);
        result = 31 * result + Objects.hashCode(creationDate);
        result = 31 * result + Objects.hashCode(salary);
        result = 31 * result + Objects.hashCode(position);
        result = 31 * result + Objects.hashCode(status);
        result = 31 * result + Objects.hashCode(organization);
        return result;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", salary=" + salary +
                ", position=" + position +
                ", status=" + status +
                ", organization=" + organization +
                '}';
    }

    @Override
    public int compareTo(Worker o) {
        return this.id.compareTo(o.id);
    }

    private void validateFields(String name, Coordinates coordinates, Float salary, Position position,
                                Status status, Organization organization) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Поле не может быть пустым или иметь значение null!");
        }
        if (coordinates == null) {
            throw new IllegalArgumentException("Поле не может иметь значение null!");
        }
        if (salary == null || salary < 0) {
            throw new IllegalArgumentException("Поле не может иметь значение null или быть меньше 0!");
        }
        if (position == null) {
            throw new IllegalArgumentException("Поле не может иметь значение null!");
        }
        if (status == null) {
            throw new IllegalArgumentException("Поле не может иметь значение null!");
        }
        if (organization == null) {
            throw new IllegalArgumentException("Поле не может иметь значение null!");
        }
    }


}