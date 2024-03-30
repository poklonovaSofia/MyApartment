package models;

import database.DbConnection;
import entities.Room;
import utils.ApartmentNotAddedException;
import utils.RoomNotAddedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class RoomModel {
    public Room addRoom(Room room) throws RoomNotAddedException {
        Connection connection;
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "INSERT INTO rooms(title, idTypeOfRoom, idApartment) VALUES(?,?,?)";
        int userId = -1;
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, room.getTitle());
            pstmt.setInt(2, room.getIdType());
            pstmt.setInt(3, room.getApartmentId());

            pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                room.setId(generatedKeys.getInt(1));
                return room;

            } else {
                throw new SQLException("Failed to create room");
            }
        } catch (SQLException e) {

            throw new RoomNotAddedException("Failed to create room");
        }
    }


}
