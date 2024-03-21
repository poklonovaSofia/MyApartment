package models;

import database.DbConnection;
import entities.User;
import utils.UserNotAddedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    Connection connection;
    public User addUser(User user) throws UserNotAddedException {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "INSERT INTO users(username, password, email) VALUES(?,?,?)";
        int userId = -1;
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));

            } else {
                throw new SQLException("Failed to retrieve user ID after insertion");
            }
        } catch (SQLException e) {

            throw new UserNotAddedException("Failed to create user");
        }
        return null;
    }
    public int getIdUserByUsernameEmail(User user) {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT id  FROM users WHERE username = ? OR email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public User findUser(User user) {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT username, id FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setId(rs.getInt("id"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
