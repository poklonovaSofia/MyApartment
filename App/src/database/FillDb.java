package database;

import entities.Furniture;
import utils.UserNotAddedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    public void insertTypesOfFurnitures() {
        Connection connection = DbConnection.getDatabaseConnection().getConnection();
        String[] furnitureTypes = {
                "Chair",
                "Table",
                "Wardrobe",
                "Dresser",
                "Sink",
                "Toilet",
                "Shelf",
                "Lamp",
                "Mirror",
                "Stand",
                "Stool",
                "Shower",
                "Couch",
                "Bed",
                "Drawer"
        };
        String sql = "INSERT INTO typesOfFurnitures (typeOfFurniture, image) VALUES (?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (String furnitureType : furnitureTypes) {
                pstmt.setString(1, furnitureType);
                pstmt.setString(2, "/images/typeOfFurniture/" +furnitureType.substring(0, 1).toLowerCase() + furnitureType.substring(1) + ".png");
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void insertFurnitures(List<Furniture> furnitureList) {
        Connection connection = DbConnection.getDatabaseConnection().getConnection();

        String sql = "INSERT INTO furnitures (title, description, image, width, height, color, material, idFurnitureType) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (Furniture furniture : furnitureList) {
                pstmt.setString(1, furniture.getTitle());
                pstmt.setString(2, furniture.getDescription());
                pstmt.setString(3, furniture.getImage());
                pstmt.setFloat(4, furniture.getWidth());
                pstmt.setFloat(5, furniture.getHeight());
                pstmt.setString(6, furniture.getColor());
                pstmt.setString(7, furniture.getMaterial());
                pstmt.setInt(8, furniture.getIdFurnitureType());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
