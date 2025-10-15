package itmo.studying.utils;

/**
 * Управляет коллекцией работников в памяти: загрузка/сохранение,
 * операции вставки, обновления и удаления, а также агрегирующие запросы.
 */

import itmo.studying.data.Status;
import itmo.studying.data.Worker;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionManager {
    private HashMap<Long, Worker> hashMap = new HashMap<>();
    private FileManager fileManager;
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;

    public CollectionManager(FileManager fileManager) {
        this.fileManager = fileManager;

        loadCollection();
    }

    public HashMap<Long, Worker> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<Long, Worker> hashMap) {
        this.hashMap = hashMap;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    public void setLastInitTime(LocalDateTime lastInitTime) {
        this.lastInitTime = lastInitTime;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public void setLastSaveTime(LocalDateTime lastSaveTime) {
        this.lastSaveTime = lastSaveTime;
    }

    public int collectionSize() {
        return hashMap.size();
    }

    public String getCollectionType() {
        return hashMap.getClass().getName();
    }

    public Worker getById(Long id) {
        if (id == null)
            throw new IllegalArgumentException();
        return hashMap.get(id);
    }

    public Worker getByValue(Worker workerToFind) {
        if (workerToFind == null) throw new IllegalArgumentException();
        for (Worker worker : hashMap.values()) {
            if (worker.equals(workerToFind)) {
                return worker;
            }
        }
        return null;
    }

    public void insertByKey(Long key, Worker worker) {
        if (key == null || worker == null) {
            throw new IllegalArgumentException("Значения не могут быть null!");
        } else if (hashMap.containsKey(key)) {
            throw new IllegalArgumentException("Такой ключ уже существует!");
        } else hashMap.put(key, worker);
    }

    public Worker updateById(Long id, Worker worker) {
        if (!hashMap.containsKey(id)) {
            throw new IllegalArgumentException("Такого ключа не существует!");
        }
        if (id == null || worker == null) {
            throw new IllegalArgumentException("Значения не могут быть null!");
        }
        return hashMap.put(id, worker);
    }

    public void removeByKey(Long key) {
        if (key == null) throw new IllegalArgumentException("Такого ключа не существует");
        hashMap.remove(key);
    }

    public void clear() {
        hashMap.clear();
    }

    public void save() {
        fileManager.writeCollection(hashMap);
        lastSaveTime = LocalDateTime.now();
    }

    public void exit() {
        System.exit(0);
    }

    public void replaceIfGreater(Long key, Worker worker) {
        if (hashMap.get(key) == null || worker == null) {
            throw new IllegalArgumentException("Значения по такому ключу или не существует, или значение элемента null!");
        }
        if (hashMap.get(key).compareTo(worker) < 0)
            hashMap.put(key, worker);
    }

    public void removeGreaterKey(Long key) {
        if (key == null) throw new IllegalArgumentException("Значение ключа не может быть null");
        Iterator<Map.Entry<Long, Worker>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getKey() > key) {
                hashMap.remove(key);
            }
        }
    }

    public void removeAllByStatus(Status status) {
        List<Long> keysToRemove = new ArrayList<>();

        for (Map.Entry<Long, Worker> entry : hashMap.entrySet()) {
            if (entry.getValue().getStatus() == status)
                keysToRemove.add(entry.getKey());
        }

        for (Long key : keysToRemove) {
            hashMap.remove(key);
        }
    }

    public void groupCountingName() {
        Map<String, List<Worker>> grouped = hashMap.values().stream()
                .collect(Collectors.groupingBy(Worker::getName));

        System.out.println("Количество групп (уникальных имён): " + grouped.size());
        grouped.forEach((name, workerList) ->
                System.out.println("Имя: " + name + " — работников: " + workerList.size())
        );
    }

    public int countLessThanSalary(Float salary) {
        if (salary == null || salary < 0)
            throw new IllegalArgumentException("Значение поля salary не может быть null!");

        int counter = 0;
        Iterator<Map.Entry<Long, Worker>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue().getSalary().compareTo(salary) < 0) {
                counter++;
            }
        }
        return counter;
    }

    private void loadCollection() {
        this.hashMap = fileManager.readCollection();
        try {
            int maxId = hashMap.values().stream()
                    .map(Worker::getId)
                    .filter(Objects::nonNull)
                    .max(Integer::compareTo)
                    .orElse(0);
            Worker.resetIdCounter(maxId);
        } catch (Exception ignored) {
        }
        lastInitTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "CollectionManager (класс для работы с коллекцией)";
    }
}
