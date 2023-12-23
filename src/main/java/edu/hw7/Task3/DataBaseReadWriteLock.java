package edu.hw7.Task3;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataBaseReadWriteLock implements PersonDataBase {
    private final Map<Integer, Person> data = new ConcurrentHashMap<>();
    private final Map<String, Set<Integer>> nameIndex = new HashMap<>();
    private final Map<String, Set<Integer>> addressIndex = new HashMap<>();
    private final Map<String, Set<Integer>> phoneIndex = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public DataBaseReadWriteLock() {

    }

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            data.put(person.id(), person);

            addToMap(nameIndex, person.name(), person.id());
            addToMap(addressIndex, person.address(), person.id());
            addToMap(phoneIndex, person.phoneNumber(), person.id());
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = data.get(id);
            if (person != null) {
                data.remove(id);

                removeFromMap(nameIndex, person.name(), id);
                removeFromMap(addressIndex, person.address(), id);
                removeFromMap(phoneIndex, person.phoneNumber(), id);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return find(nameIndex, name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return find(addressIndex, address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return find(phoneIndex, phone);
        } finally {
            lock.readLock().unlock();
        }
    }

    private void addToMap(Map<String, Set<Integer>> map, String key, int id) {
        map.computeIfAbsent(key, k -> ConcurrentHashMap.newKeySet()).add(id);
    }

    private void removeFromMap(Map<String, Set<Integer>> map, String key, int id) {
        map.computeIfPresent(key, (k, ids) -> {
            ids.remove(id);
            if (ids.isEmpty()) {
                return null;
            }
            return ids;
        });
    }

    private List<Person> find(Map<String, Set<Integer>> map, String key) {
        synchronized (this) {
            Set<Integer> ids = map.getOrDefault(key, Collections.emptySet());
            return ids.stream()
                .map(data::get)
                .filter(Objects::nonNull)
                .toList();
        }
    }

    public int getTotalCount() {
        return data.size();
    }
}
