package edu.hw4.Task19;

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

    public static Map<String, Set<ValidationError>> getErrorNames(List<Animal> input) {
        if (input == null) {
            return null;
        }
        return input.stream()
            .filter(animal -> hasValidationErrors(animal.name()))
            .collect(Collectors.toMap(
                Animal::name,
                animal -> getValidationErrors(animal.name()),
                (existing, replacement) -> {
                    existing.addAll(replacement);
                    return existing;
                },
                LinkedHashMap::new
            ));
    }

    private static boolean hasValidationErrors(String name) {
        return (!name.matches(CORRECT_NAME_PATTERN))
            || (Character.isLowerCase(name.charAt(0)));
    }

    private static Set<ValidationError> getValidationErrors(String name) {
        Set<ValidationError> errors = new HashSet<>();

        if (!name.matches(CORRECT_NAME_PATTERN)) {
            errors.add(new ValidationError(ValidationError.Type.INVALID_CHARACTERS_ERROR, "invalid character"));
        }

        if (Character.isLowerCase(name.charAt(0))) {
            errors.add(new ValidationError(ValidationError.Type.CAPITALIZATION_ERROR, "wrong capitalization"));
        }

        return errors;
    }


}
