package models;

import database.DbConnection;
import entities.Apartment;
import entities.User;
import entities.UsersVote;
import utils.RoomNotAddedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersVoteModel {
    Connection connection;
    public List<UsersVote> getAllApartmentById(int idUser) {
        List<UsersVote> usersVotes = new ArrayList<>();
        connection = DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT id, idUser, idApartment FROM usersVote WHERE idUser = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                UsersVote usersVote = new UsersVote();
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

    public UsersVote addUsersVote(int id, int id1) {
        connection= DbConnection.getDatabaseConnection().getConnection();
        String sql = "INSERT INTO usersVote(idUser, idApartment) VALUES(?,?)";
        UsersVote usersVote = new UsersVote();
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
