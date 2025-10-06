package itmo.studying.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import itmo.studying.data.Worker;
import itmo.studying.exceptions.InvalidFileException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileManager {
    private final String envName;
    private final Gson gson = new Gson();

    public FileManager(String envName) {
        this.envName = envName;
    }

    public HashMap<Integer, Worker> readCollection(){
        String path = System.getenv(envName);
        if (path == null) {
            throw new InvalidFileException("Переменная окружения " + envName + " не задана!");
        }

        File file = new File(path);
        if (!file.exists()) {
            throw new InvalidFileException("Файл не существует: " + path);
        }

        try (Scanner scanner = new Scanner(file)) {
            StringBuilder jsonBuilder = new StringBuilder();
            while (scanner.hasNextLine()){
                jsonBuilder.append(scanner.nextLine());
            }
            String json = jsonBuilder.toString();
            Type collectionType = new TypeToken<HashMap<Integer, Worker>>(){}.getType();
            return gson.fromJson(json, collectionType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeCollection(Map<?, ?> map) {
        String path = System.getenv(envName);
        if (path == null) {
            throw new InvalidFileException("Переменная окружения " + envName + " не задана!");
        }

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path))) {
            writer.write(gson.toJson(map));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не существует или не найден!");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи коллекции в файл: " + path, e);
        }
    }

    @Override
    public String toString() {
        return "FileManager (класс для работы с файлом)";
    }
}
