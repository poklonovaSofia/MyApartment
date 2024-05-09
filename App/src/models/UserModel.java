package models;

import database.DbConnection;
import entities.User;
import utils.UserNotAddedException;
import utils.UserNotUpdated;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Handles database operations related to users.
 */
public class UserModel {
    Connection connection;
    /**
     * Adds a new user to the database.
     *
     * @param user The user object to add
     * @return The user object after addition
     * @throws UserNotAddedException If unable to add the user
     * @see User
     */
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
                return user;
            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            throw new UserNotAddedException("Failed to create user");
        }
        return null;
    }
    /**
     * Retrieves the user ID by username or email.
     *
     * @param user The user object containing username or email
     * @return The user ID
     * @see User
     */
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
    /**
     * Finds a user by email and password.
     *
     * @param user The user object containing email and password
     * @return The found user object
     * @see User
     */
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
    /**
     * Updates the username of a user.
     *
     * @param id   The ID of the user
     * @param text The new username
     * @throws UserNotUpdated If unable to update the username
     */
    public void updateUsername(int id, String text) throws UserNotUpdated {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "UPDATE users SET username = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, text);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new UserNotUpdated("Such username already exists");

        }
    }
    /**
     * Updates the email of a user.
     *
     * @param id   The ID of the user
     * @param text The new email
     * @throws UserNotUpdated If unable to update the email
     */
    public void updateEmail(int id, String text) throws UserNotUpdated {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "UPDATE users SET email = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, text);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new UserNotUpdated("Such email already exists");
        }
    }

    public User getUser(int idUser) {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT username, email  FROM users WHERE id= ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setId(idUser);
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
