package models;

import database.DbConnection;
import entities.RoomType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeModel {
    public  List<RoomType> getAllTypes() {
        List<RoomType> roomTypes = new ArrayList<>();
        Connection connection = DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT id, typeOfRoom FROM typesOfRooms";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                RoomType roomType = new RoomType(rs.getInt("id"), rs.getString("typeOfRoom"));
                roomTypes.add(roomType);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return roomTypes;
    }
}
