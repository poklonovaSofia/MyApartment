package utils;

public interface Subject {
    void registerObserver(int userId);
    void notifyObservers(int userId, String message, int idApartment);

}
