package utils;

/**
 * Exception thrown when an apartment cannot be added to the database.
 * This could be due to various reasons such as connectivity issues with the database
 */
public class ApartmentNotAddedException extends Exception{
    public ApartmentNotAddedException(String message)
    {
        super(message);
    }
}

