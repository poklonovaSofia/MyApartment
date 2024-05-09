package models;

import database.DbConnection;
import entities.Apartment;
import entities.User;

import entities.Vote;
import utils.RoomNotAddedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Handles database operations related to user votes.
 */
public class UsersVoteModel {
    Connection connection;
    /**
     * Retrieves all votes by a specific user.
     *
     * @param idUser The ID of the user
     * @return A list of user votes
     * @see Vote
     */
    public List<Vote> getAllApartmentById(int idUser) {
        List<Vote> usersVotes = new ArrayList<>();
        connection = DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT id, idUser, idApartment FROM usersVote WHERE idUser = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Vote usersVote = new Vote();
                usersVote.setId( rs.getInt("id"));
                usersVote.setIdUser( rs.getInt("idUser"));
                usersVote.setIdApartment( rs.getInt("idApartment"));

                usersVotes.add(usersVote);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return usersVotes;
    }
    /**
     * Adds a vote for a user.
     *
     * @param id  The ID of the apartment
     * @param id1 The ID of the user
     * @return The user vote object after addition
     * @see Vote
     */
    public Vote addUsersVote(int id, int id1) {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "INSERT INTO usersVote(idUser, idApartment) VALUES(?,?)";
        Vote usersVote = new Vote();
        usersVote.setIdApartment(id);
        usersVote.setIdUser(id1);
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id1);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                usersVote.setId(generatedKeys.getInt(1));
                return usersVote;

            } else {
                throw new SQLException("Failed to create room");
            }
        } catch (SQLException e) {


        }
        return null;
    }
    public int findVoteId(int idUser, int idApartment) {
        int voteId = -1;
        connection = DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT id FROM usersVote WHERE idUser = ? AND idApartment = ? LIMIT 1";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            pstmt.setInt(2, idApartment);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                voteId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voteId;
    }
    /**
     * Deletes a user's vote for an apartment.
     *
     * @param id  The ID of the apartment
     * @param id1 The ID of the user
     * @return The ID of the deleted vote
     */
    public int deleteUsersVote(int id, int id1) {
        connection = DbConnection.getDatabaseConnection().getConnection();
        String sql = "DELETE FROM usersVote WHERE idUser = ? AND idApartment = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, id1);
            pstmt.setInt(2, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
