package itmo.studying.utils;

/**
 * Читает и пишет коллекцию в JSON-файл, поддерживает (де)сериализацию LocalDate
 * через кастомный адаптер Gson.
 */

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import itmo.studying.data.Worker;
import itmo.studying.exceptions.InvalidFileException;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс для чтения и записи коллекции в файл (JSON) с поддержкой LocalDate.
 */
public class FileManager {
    private final String envName;
    private final Gson gson;

    public FileManager(String envName) {
        this.envName = envName;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
    }

    public HashMap<Long, Worker> readCollection() {
        String path = System.getenv(envName);
        System.out.println("✅ ENV " + envName + " = " + path);
        if (path == null) {
            throw new InvalidFileException("Переменная окружения " + envName + " не задана!");
        }

        File file = new File(path);
        System.out.println("📄 File exists: " + file.exists() + " | Size: " + file.length());
        if (!file.exists()) {
            throw new InvalidFileException("Файл не существует: " + path);
        }

        try (Scanner scanner = new Scanner(file)) {
            StringBuilder jsonBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                jsonBuilder.append(scanner.nextLine());
            }
            String json = jsonBuilder.toString().trim();
            if (json.isEmpty()) {
                return new HashMap<>();
            }
            Type collectionType = new TypeToken<HashMap<Long, Worker>>() {}.getType();
            HashMap<Long, Worker> map = gson.fromJson(json, collectionType);
            if (map == null) {
                return new HashMap<>();
            }
            return map;
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

    /**
     * Адаптер для сериализации/десериализации LocalDate.
     */
    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        @Override
        public JsonElement serialize(LocalDate date, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.toString());
        }

        @Override
        public LocalDate deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString());
        }
    }
}