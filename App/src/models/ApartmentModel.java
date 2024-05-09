package models;

import database.DbConnection;
import entities.Apartment;
import entities.FurnitureType;
import entities.StatiscticOfApartment;
import utils.ApartmentNotAddedException;
import utils.UserNotAddedException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApartmentModel {
    Connection connection;
    /**
     * Checks if there exists an apartment name for the user with the specified ID.
     *
     * @param title  The apartment name to check
     * @param userId The ID of the user
     * @return true if an apartment with the given name and user exists; otherwise, false
     */
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

     /**
     * Adds an apartment to the database.
     *
     * @param apartment The apartment object to add
     * @return The apartment object after addition
     * @throws ApartmentNotAddedException If unable to add the apartment
     * @see Apartment
     */
    public Apartment addApartment(Apartment apartment) throws ApartmentNotAddedException {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "INSERT INTO apartments(title, description, idUser, created_at, updated_at, isPublic, numberOfVotes) VALUES(?,?,?,?,?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, apartment.getTitle());
            pstmt.setString(2, apartment.getDescription());
            pstmt.setInt(3, apartment.getInheritId());
            apartment.setCreatedAt(LocalDateTime.now().toString());
            apartment.setEditedAt(apartment.getCreatedAt());
            pstmt.setString(4, apartment.getCreatedAt());
            pstmt.setString(5, apartment.getCreatedAt());
            pstmt.setBoolean(6, apartment.getIsPublic());
            pstmt.setInt(7, 0);
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
    /**
     * Updates the last modified date of the apartment with the specified ID.
     * @param id The ID of the apartment to update
     */
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
    /**
     * Retrieves all apartments belonging to a specific user.
     *
     * @param idUser The ID of the user
     * @return A list of apartments belonging to the user
     * @see Apartment
     */
    public List<Apartment> getAllApartByIdUser(int idUser) {
        List<Apartment> apartmentList = new ArrayList<>();
        connection = DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT id, title, description, isPublic, created_at, updated_at, numberOfVotes FROM apartments WHERE idUser = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Apartment apartment = new Apartment();
                apartment.setId( rs.getInt("id"));
                apartment.setTitle( rs.getString("title"));
                apartment.setDescription( rs.getString("description"));
                apartment.setCreatedAt( rs.getString("created_at"));
                apartment.setEditedAt(rs.getString("updated_at"));
                apartment.setInheritId(idUser);
                apartment.setIsPublic(rs.getBoolean("isPublic"));
                apartment.setNumberOfVotes(rs.getInt("numberOfVotes"));
                apartmentList.add(apartment);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return apartmentList;
    }
    /**
     * Changes the privacy state of the apartment with the specified ID.
     *
     * @param id       The ID of the apartment to update
     * @param isPublic The new privacy state (true for public, false for private)
     */
    public void changeStateOfPrivacy(int id, Boolean isPublic) {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "UPDATE apartments SET isPublic = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setBoolean(1, isPublic);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Retrieves all public apartments except those owned by a specific user.
     *
     * @param idUser The ID of the user
     * @return A list of public apartments excluding those owned by the user
     * @see Apartment
     */
    public List<Apartment> getAllApartWithoutUser(int idUser) {
        List<Apartment> apartmentList = new ArrayList<>();
        connection = DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT id, title, description, idUser, isPublic, created_at, updated_at, numberOfVotes FROM apartments WHERE idUser != ? AND isPublic = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            pstmt.setInt(2, 1);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Apartment apartment = new Apartment();
                apartment.setId( rs.getInt("id"));
                apartment.setTitle( rs.getString("title"));
                apartment.setDescription( rs.getString("description"));
                apartment.setCreatedAt( rs.getString("created_at"));
                apartment.setEditedAt(rs.getString("updated_at"));
                apartment.setInheritId(rs.getInt("idUser"));
                apartment.setIsPublic(rs.getBoolean("isPublic"));
                apartment.setNumberOfVotes(rs.getInt("numberOfVotes"));
                apartmentList.add(apartment);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return apartmentList;
    }
    /**
     * Updates the number of votes for a specific apartment.
     *
     * @param numberOfVotes The new number of votes
     * @param id The ID of the apartment
     */
    public void changeNumberOfVotes(int numberOfVotes, int id) {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "UPDATE apartments SET numberOfVotes = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, numberOfVotes);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Retrieves a list of public apartments along with their statistics.
     *
     * @return A list of public apartments with their statistics
     * @see StatiscticOfApartment
     */
    public List<StatiscticOfApartment> getAllPublicAp() {
        List<StatiscticOfApartment> statiscticOfApartments = new ArrayList<>();
        connection = DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT id, title, idUser, numberOfVotes FROM apartments WHERE isPublic = ? ORDER BY numberOfVotes DESC LIMIT ?;";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, 1);
            pstmt.setInt(2, 20);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                StatiscticOfApartment statiscticOfApartment = new StatiscticOfApartment();
                statiscticOfApartment.setId( rs.getInt("id"));
                statiscticOfApartment.setTitle( rs.getString("title"));
                statiscticOfApartment.setInheritId( rs.getInt("idUser"));
                statiscticOfApartment.setNumberOfVotes( rs.getInt("numberOfVotes"));
                statiscticOfApartments.add(statiscticOfApartment);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return statiscticOfApartments;
    }
}
