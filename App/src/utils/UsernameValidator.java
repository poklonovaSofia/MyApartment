package utils;

import java.util.regex.Pattern;

public class UsernameValidator implements Validator{
    private static  String unRegex = "^[a-zA-Z_][a-zA-Z_0-9]{4,19}$";
    private static Pattern unPattern = Pattern.compile(unRegex);
    public boolean validate(String value) throws UsernameException {
        if (!(unPattern.matcher(value).matches())) {
            throw new UsernameException("Not a valid name");
        }
        return true;

    }
}
