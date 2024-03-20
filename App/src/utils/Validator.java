package utils;

public interface Validator {
    public boolean validate(String value) throws PasswordException, EmailException, UsernameException;
}
