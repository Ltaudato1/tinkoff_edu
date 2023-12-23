package edu.hw7.Task3;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DataBaseSynchronized implements PersonDataBase {
    private final Map<Integer, Person> data = new ConcurrentHashMap<>();
    private final Map<String, Set<Integer>> nameIndex = new HashMap<>();
    private final Map<String, Set<Integer>> addressIndex = new HashMap<>();
    private final Map<String, Set<Integer>> phoneIndex = new HashMap<>();

    public DataBaseSynchronized() {

    }

    @Override
    public void add(Person person) {
        synchronized (this) {
            data.put(person.id(), person);

            addToMap(nameIndex, person.name(), person.id());
            addToMap(addressIndex, person.address(), person.id());
            addToMap(phoneIndex, person.phoneNumber(), person.id());
        }
    }

    @Override
    public void delete(int id) {
        synchronized (this) {
            Person person = data.get(id);
            if (person != null) {
                data.remove(id);

                removeFromMap(nameIndex, person.name(), id);
                removeFromMap(addressIndex, person.address(), id);
                removeFromMap(phoneIndex, person.phoneNumber(), id);
            }
        }
    }

    @Override
    public List<Person> findByName(String name) {
        return find(nameIndex, name);
    }

    @Override
    public List<Person> findByAddress(String address) {
        return find(addressIndex, address);
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return find(phoneIndex, phone);
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
