package trading.factory;

import trading.exception.NullPointerGuardException;

import java.lang.reflect.Field;
import java.util.List;

// Used to validate that all the field of a given object are not null.
public class NullPointerGuard {
    static public void validate(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(obj) == null) {
                    throw new NullPointerGuardException(field);
                }
            }
            // Should never happend since we set setAccessible to true.
            catch (IllegalAccessException e) {
            }
        }
    }

    static public void validate(Object obj, List<String> ignoredFields) {
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
            }
            // Should never happend since we set setAccessible to true.
            catch (IllegalAccessException e) {
            }
        }
    }
}
