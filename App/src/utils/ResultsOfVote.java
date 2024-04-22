package utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ResultsOfVote implements Subject{
    private Map<Integer, Set<Integer>> userObserversMap = new HashMap<>();
    private Map<Integer, UserNotificationService> observers = new HashMap<>(); // Мапа для зберігання спостерігачів, ключ - ідентифікатор користувача

    @Override
    public void registerObserver(int userId) {
        // Перевіряємо, чи є такий користувач в мапі
        if (!userObserversMap.containsKey(userId)) {
            userObserversMap.put(userId, new HashSet<>());
        }

        userObserversMap.get(userId).add(userId);

        UserNotificationService observer = new UserNotificationService(userId);
        observers.put(userId, observer);
    }

    @Override
    public void notifyObservers(int userId, String message, int idApartment) {
        // Отримуємо список спостерігачів для даного користувача
        Set<Integer> userIds = userObserversMap.get(userId);
        if (userIds != null) {
            // Повідомляємо всіх спостерігачів
            for (int observerUserId : userIds) {
                UserNotificationService observer = observers.get(observerUserId);
                if (observer != null) {
                    observer.update(userId, message, idApartment);
                }
            }
        }
    }
}
