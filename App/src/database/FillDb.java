package database;

import utils.UserNotAddedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FillDb {

    public void insertTypesOfRooms() {
        Connection connection = DbConnection.getDatabaseConnection().getConnection();
        String[] roomTypes = {
                "living room",
                "kitchen",
                "bedroom",
                "bathroom",
                "office",
                "dining room",
                "study",
                "guest room"
        };
        String sql = "INSERT INTO typesOfRooms (typeOfRoom) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (String roomType : roomTypes) {
                pstmt.setString(1, roomType);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
