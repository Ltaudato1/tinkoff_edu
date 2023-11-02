package edu.hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Task5 {
    private Task5() {

    }

    public static List<Contact> parseContacts(List<String> input, String type) {
        if (input == null) {
            return null;
        }
        List<Contact> result = new ArrayList<>();
        for (String fullName : input) {
            result.add(new Contact(fullName));
        }
        result.sort(new ContactComparator());
        if (type.equals("DESC")) {
            Collections.reverse(result);
        }
        return result;
    }

    private static class ContactComparator implements Comparator<Contact> {
        @Override
        public int compare(Contact o1, Contact o2) {
            String[] nameSurname1 = o1.fullName().split(" ");
            String[] nameSurname2 = o2.fullName().split(" ");
            String surname1 = (nameSurname1.length == 2) ? nameSurname1[1] : nameSurname1[0];
            String surname2 = (nameSurname2.length == 2) ? nameSurname2[1] : nameSurname2[0];
            return surname1.compareTo(surname2);
        }
    }

    public record Contact(String fullName) { }
}
