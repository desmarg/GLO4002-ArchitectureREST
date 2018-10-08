package trading.factory;

import trading.exception.NullPointerGuardException;

import java.lang.reflect.Field;
import java.util.List;

// Used to validate that all the field of a given object are not null.
public class NullPointerGuard {
    public static void validate(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(obj) == null) {
                    throw new NullPointerGuardException(field);
                }
            } catch (IllegalAccessException e) {
                // Never happens
            }
        }
    }

    public static void validate(Object obj, List<String> ignoredFields) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            System.out.println(field.getName());
            if (ignoredFields.contains(field.getName())) {
                continue;
            }

            field.setAccessible(true);
            try {
                if (field.get(obj) == null) {
                    throw new NullPointerGuardException(field);
                }
            } catch (IllegalAccessException e) {
                // Never happens
            }
        }
    }
}
