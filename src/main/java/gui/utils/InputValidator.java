package gui.utils;

import org.apache.commons.lang3.StringUtils;

public class InputValidator {
    public static boolean isEmpty(String input) {
        String trimmedInput = StringUtils.trim(input);
        return trimmedInput.isEmpty();
    }

    public static boolean isNumericValid(String input) {
        if (isEmpty(input))
            return false;

        return StringUtils.isNumeric(input);
    }
}
