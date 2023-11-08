package edu.hw4.Task20;

import edu.hw4.Animal;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Apply {
    private Apply() {

    }

    private static final String CORRECT_NAME_PATTERN = "[a-zA-Z\\s-]+";

    public static Map<String, String> getErrorNames(List<Animal> input) {
        if (input == null) {
            return null;
        }
        return input.stream()
            .filter(animal -> hasValidationErrors(animal.name()))
            .collect(Collectors.toMap(
                Animal::name,
                animal -> {
                    Set<ValidationErrorUserFriendly> errors = getValidationErrors(animal.name());
                    StringBuilder builder = new StringBuilder();
                    for (ValidationErrorUserFriendly error : errors) {
                        builder.append(error.getStringError());
                    }
                    return builder.toString();
                },
                (existing, replacement) -> {
                    return existing + replacement;
                },
                LinkedHashMap::new
            ));
    }

    private static boolean hasValidationErrors(String name) {
        return (!name.matches(CORRECT_NAME_PATTERN))
            || (Character.isLowerCase(name.charAt(0)));
    }

    private static Set<ValidationErrorUserFriendly> getValidationErrors(String name) {
        Set<ValidationErrorUserFriendly> errors = new HashSet<>();

        if (!name.matches(CORRECT_NAME_PATTERN)) {
            errors.add(new ValidationErrorUserFriendly(ValidationErrorUserFriendly.Type.INVALID_CHARACTERS_ERROR));
        }

        if (Character.isLowerCase(name.charAt(0))) {
            errors.add(new ValidationErrorUserFriendly(ValidationErrorUserFriendly.Type.CAPITALIZATION_ERROR));
        }

        return errors;
    }


}
