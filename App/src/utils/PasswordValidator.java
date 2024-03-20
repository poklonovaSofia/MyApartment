package utils;

import java.util.regex.Pattern;

public class PasswordValidator implements Validator{
    private static  String passRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static  Pattern passPattern = Pattern.compile(passRegex);
    public boolean validate(String value) throws PasswordException {

        if (!(passPattern.matcher(value).matches())) {
            throw new PasswordException("Not a valid password");
        }
        return true;
    }
}
