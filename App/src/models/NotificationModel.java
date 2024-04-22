package models;

import database.DbConnection;
import entities.Notification;
import entities.UsersVote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationModel {
    Connection connection;
    public void addNotification(Notification notification) {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "INSERT INTO notifications(idUser, idApartment, message) VALUES(?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, notification.getIdUser());
            pstmt.setInt(2, notification.getIdApartment());
            pstmt.setString(3, notification.getMessage());
            pstmt.executeUpdate();

        } catch (SQLException e) {


        }
    }
    public List<Notification> getAllNotificationsForUser(int id) {
        List<Notification> usersNotifications = new ArrayList<>();
        connection = DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT idApartment, message FROM notifications WHERE idUser = ? ORDER BY id DESC";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Notification notification=new Notification(id, rs.getInt("idApartment"), rs.getString("message"));
                usersNotifications.add(notification);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return usersNotifications;
    }
}
