package models;

import database.DbConnection;
import entities.Apartment;
import utils.ApartmentNotAddedException;
import utils.UserNotAddedException;

import java.sql.*;
import java.time.LocalDateTime;

public class ApartmentModel {
    Connection connection;
    public boolean existUsersApartmentName(String title, int userId) {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT COUNT(*) AS count FROM apartments WHERE title = ? AND idUser = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, title );
            pstmt.setInt(2, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                return rs.getInt("count") > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Apartment addApartment(Apartment apartment) throws ApartmentNotAddedException {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "INSERT INTO apartments(title, description, idUser, created_at, updated_at) VALUES(?,?,?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, apartment.getTitle());
            pstmt.setString(2, apartment.getDescription());
            pstmt.setInt(3, apartment.getUserId());
            apartment.setCreatedAt(LocalDateTime.now().toString());
            apartment.setEditedAt(apartment.getCreatedAt());
            pstmt.setString(4, apartment.getCreatedAt());
            pstmt.setString(5, apartment.getCreatedAt());
            pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                apartment.setId(generatedKeys.getInt(1));
                return apartment;

            } else {
                throw new SQLException("Failed to create apartment");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ApartmentNotAddedException("Failed to create apartment");
        }
    }
    public void updateApartment(int id) {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "UPDATE apartments SET updated_at = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, LocalDateTime.now().toString());
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
