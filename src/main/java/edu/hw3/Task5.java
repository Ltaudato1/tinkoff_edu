package edu.hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Task5 {
    private Task5() {

    }

    public static ArrayList<Contact> parseContacts(ArrayList<String> input, String type) {
        if (input == null) {
            return null;
        }
        LinkedHashMap<String, Object> map = new LinkedHashMap<>(); // Фамилия -> Имя Фамилия
        for (String fullName : input) {
            String[] nameSurname = fullName.split(" ");
            if (nameSurname.length == 2) {
                map.put(nameSurname[1], fullName);
            } else {
                map.put(nameSurname[0], fullName);
            }
        }
        map = sortMapByKey(map, type);

        ArrayList<Contact> result = new ArrayList<>();
        for (Object fullName : map.values()) {
            result.add(new Contact((String) fullName));
        }
        return result;
    }

    public static LinkedHashMap<String, Object> sortMapByKey(LinkedHashMap<String, Object> unsortedMap, String type) {
        if (type.equals("ASC")) {
            return unsortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
                ));
        } else {
            return unsortedMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
                ));
        }
    }

    public static record Contact(String fullName) { }
}
