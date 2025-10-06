package itmo.studying.utils;

import itmo.studying.data.Coordinates;
import itmo.studying.data.Organization;
import itmo.studying.data.Position;
import itmo.studying.data.Status;
import itmo.studying.exceptions.IllegalStateException;
import itmo.studying.exceptions.IncorrectInputInScriptException;
import itmo.studying.exceptions.MustBeNotEmptyException;
import itmo.studying.exceptions.NotInDeclaredLimitsException;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class WorkerRequests {
    private Scanner userScanner;
    private boolean fileMode;

    public WorkerRequests(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    public Scanner getUserScanner() {
        return userScanner;
    }

    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    public void setFileMode() {
        fileMode = true;
    }

    public void setUserMode() {
        fileMode = false;
    }

    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Console.println("Введи имя работника!");
                Console.print(">");
                if (!userScanner.hasNextLine()) throw new NoSuchElementException();
                name = userScanner.nextLine().trim();
                if (name.isEmpty()) throw new IncorrectInputInScriptException();
                if (fileMode) Console.println("Имя работника: " + name);
                break;
            } catch (NoSuchElementException e) {
                Console.println("Имя работника не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException e) {
                Console.println("Поле должно быть заполнено!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException e) {
                Console.printErr("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    public Long askX() throws IncorrectInputInScriptException {
        Long x;
        String strX;
        while (true) {
            try {
                Console.println("Введите координату 'X'!");
                Console.print(">");
                if (!userScanner.hasNextLine()) throw new NoSuchElementException();
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println("Координата X: " + strX);
                x = Long.parseLong(strX);
                break;
            } catch (NoSuchElementException e) {
                Console.printErr("Координата 'X' не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException e) {
                Console.printErr("Значение должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException e) {
                Console.printErr("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;
    }

    public float askY() throws IncorrectInputInScriptException {
        String strY;
        float y;
        while (true) {
            try {
                Console.println("Введите координату 'Y'!");
                Console.print(">");
                if (userScanner.hasNextLine()) throw new NoSuchElementException();
                strY = userScanner.nextLine().trim();
                if (fileMode) Console.println("Координата Y: " + strY);
                y = Float.parseFloat(strY);
                break;
            } catch (NoSuchElementException e) {
                Console.printErr("Координата 'Y' не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException e) {
                Console.printErr("Значение должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException e) {
                Console.printErr("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }

    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        return new Coordinates(askX(), askY());
    }

    public Float askSalary() throws IncorrectInputInScriptException {
        Float salary;
        String strSalary;
        while (true) {
            try {
                Console.println("Введите зарплату работника!");
                Console.print(">");
                if (!userScanner.hasNextLine()) throw new NoSuchElementException();
                strSalary = userScanner.nextLine().trim();
                if (fileMode) Console.println("Зарплата работника :" + strSalary);
                salary = Float.parseFloat(strSalary);
                if (salary < 0) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException e) {
                Console.printErr("Значение зарплаты не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException e) {
                Console.printErr("Формат числа неправильный (0.0f)");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException e) {
                Console.printErr("Зарплата должна быть положительным числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException e) {
                Console.printErr("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return salary;
    }

    public Position askPosition() throws IncorrectInputInScriptException {
        String strPosition;
        Position position;
        while (true) {
            try {
                Console.println("Список позиций работника - " + Position.nameList());
                Console.println("Выберете позицию!");
                Console.print(">");
                if (!userScanner.hasNextLine()) throw new NoSuchElementException();
                strPosition = userScanner.nextLine().trim();
                if (fileMode) Console.println("Позиция работника: " + strPosition);
                position = Position.valueOf(strPosition.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                Console.printErr("Заданное значение не входите в список актуальных позиций!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NoSuchElementException e) {
                Console.printErr("Позиция работника не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException e) {
                Console.printErr("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return position;
    }

    public Status askStatus() throws IncorrectInputInScriptException {
        String strStatus;
        Status status;
        while (true){
            try {
                Console.println("Список статусов сотрудника - " + Status.nameList());
                Console.println("Введите статус сотрудника!");
                Console.print(">");
                if (!userScanner.hasNextLine()) throw new NoSuchElementException();
                strStatus = userScanner.nextLine().trim();
                if (fileMode) Console.println("Позиция работника: " + strStatus);
                status = Status.valueOf(strStatus.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                Console.printErr("Заданное значение не входите в список актуальных статусов работника!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NoSuchElementException e) {
                Console.printErr("Статус работника не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException e) {
                Console.printErr("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return status;
    }

    public String askOrganizationFullName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Console.println("Введите полное название организации!");
                Console.println(">");
                if (!userScanner.hasNextLine()) throw new NoSuchElementException();
                name = userScanner.nextLine().trim();
                if (name.isEmpty()) throw new MustBeNotEmptyException();
                if (fileMode) Console.println("Полное название организации: " + name);
                break;
            } catch (NoSuchElementException e) {
                Console.printErr("Название организации не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException e) {
                Console.println("Поле должно быть заполнено!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException e) {
                Console.printErr("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    public float askAnnualTurnover() throws IncorrectInputInScriptException {
        float annualTurnover;
        String str;
        while (true) {
            try {
                Console.println("Введите годовой оборот компании!");
                Console.print(">");
                if (!userScanner.hasNextLine()) throw new NoSuchElementException();
                str = userScanner.nextLine().trim();
                if (str.isEmpty()) throw new MustBeNotEmptyException();
                annualTurnover = Float.parseFloat(str);
                if (annualTurnover < 0) throw new NotInDeclaredLimitsException();
                if (fileMode) Console.println("Годовой оборот организации: " + annualTurnover);
                break;
            } catch (NoSuchElementException e) {
                Console.printErr("Годовой оборот не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException | NotInDeclaredLimitsException | NumberFormatException e) {
                Console.printErr("Поле должно быть заполнено положительным значением (0.0f)");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException e) {
                Console.printErr("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return annualTurnover;
    }

    public long askEmployeesCount() throws IncorrectInputInScriptException {
        long employeesCount;
        String str;
        while (true) {
            try {
                Console.println("Введите количество сотрудников организации!");
                Console.print(">");
                if (!userScanner.hasNextLine()) throw new NoSuchElementException();
                str = userScanner.nextLine().trim();
                if (str.isEmpty()) throw new MustBeNotEmptyException();
                employeesCount = Long.parseLong(str);
                if (employeesCount < 0) throw new NotInDeclaredLimitsException();
                if (fileMode) Console.println("Количество сотрудников организации: " + employeesCount);
                break;
            } catch (NoSuchElementException e) {
                Console.printErr("Количество сотрудников не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException | NotInDeclaredLimitsException e) {
                Console.printErr("Поле должно быть заполнено и иметь положительное значение!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException e) {
                Console.printErr("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return employeesCount;
    }

    public Organization askOrganization() throws IncorrectInputInScriptException {
        return new Organization(askOrganizationFullName(), askAnnualTurnover(), askEmployeesCount());
    }

    @Override
    public String toString() {
        return "WorkerAsker (вспомогательный класс для работы с запросами в консоль на ввод и работу скрипта)";
    }
}
