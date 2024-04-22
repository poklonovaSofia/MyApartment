package utils;

import java.util.regex.Pattern;

public class EmailValidator implements Validator{
    private static final String emailRegex = "^(.+)@(.+)$";
    private static final Pattern emailPattern = Pattern.compile(emailRegex);
    public boolean validate(String value) throws EmailException {

        if (!(emailPattern.matcher(value).matches())) {
            throw new EmailException("Not a valid email");
        }
        return true;
    }
}
