package models;

import database.DbConnection;
import entities.Furniture;
import entities.FurnitureType;
import utils.RoomNotAddedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FurnitureModel {
    Connection connection;
    public List<Furniture> getAllByIdTypeOfFurniture(int id) {
        List<Furniture> furnitures = new ArrayList<>();
        connection = DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT id, title, description, image, width, height, color, material FROM furnitures WHERE idFurnitureType= ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Furniture furniture = new Furniture();
                furniture.setId(rs.getInt("id"));
                furniture.setDescription(rs.getString("description"));
                furniture.setTitle(rs.getString("title"));
                furniture.setImage(rs.getString("image"));
                furniture.setMaterial(rs.getString("material"));
                furniture.setColor(rs.getString("color"));
                furniture.setWidth(rs.getFloat("width"));
                furniture.setHeight(rs.getFloat("height"));
                furnitures.add(furniture);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return furnitures;

    }

    public void addFurniture(int f, int id) {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "INSERT INTO addedFurniture(idFurniture, idRoom) VALUES(?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, f);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {


        }
    }

    public List<Furniture> getAllFurnitureById(int id) {
        List<Integer> furnitures = new ArrayList<>();
        connection = DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT idFurniture FROM addedFurniture WHERE idRoom= ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                furnitures.add(rs.getInt("idFurniture"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return getAllById(furnitures);
    }

    private List<Furniture> getAllById(List<Integer> furnInts) {
        List<Furniture> furnitures = new ArrayList<>();
        if (furnInts == null || furnInts.isEmpty()) {
            return furnitures;
        }

        connection = DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT id, title, description, image, width, height, color, material FROM furnitures WHERE id IN (?)";
        sql = sql.replace("?", furnInts.stream().map(i -> "?").collect(Collectors.joining(",")));

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            int index = 1;
            for (Integer id : furnInts) {
                pstmt.setInt(index++, id);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Furniture furniture = new Furniture();
                    furniture.setId(rs.getInt("id"));
                    furniture.setDescription(rs.getString("description"));
                    furniture.setTitle(rs.getString("title"));
                    furniture.setImage(rs.getString("image"));
                    furniture.setMaterial(rs.getString("material"));
                    furniture.setColor(rs.getString("color"));
                    furniture.setWidth(rs.getFloat("width"));
                    furniture.setHeight(rs.getFloat("height"));
                    furnitures.add(furniture);
                }
            }
        }
     catch (SQLException e) {
        System.out.println(e.getMessage());
    }

        return furnitures;
    }

}
