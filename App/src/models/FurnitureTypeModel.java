package models;

import database.DbConnection;
import entities.FurnitureType;
import entities.RoomType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FurnitureTypeModel {
    Connection connection;
    public List<FurnitureType> getAllTypes() {
        List<FurnitureType> furnitureTypes = new ArrayList<>();
        connection = DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT id, typeOfFurniture FROM typesOfFurnitures";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FurnitureType furnitureType = new FurnitureType(rs.getInt("id"), rs.getString("typeOfFurniture"));
                furnitureTypes.add(furnitureType);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return furnitureTypes;
    }

    public int getId(String typeOfFurniture) {
        connection = DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT id FROM typesOfFurnitures WHERE typeOfFurniture = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, typeOfFurniture);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
               return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
