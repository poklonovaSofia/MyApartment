package utils;

import entities.Notification;
import models.NotificationModel;

public class UserNotificationService implements Observer {
    private NotificationModel notificationModel;
    public UserNotificationService(int idUser){}
    @Override
    public void update(int userId, String message, int idAp) {
        notificationModel=new NotificationModel();
        notificationModel.addNotification(new Notification(userId, idAp, message));
    }
}