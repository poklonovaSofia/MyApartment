package models;

import database.DbConnection;
import entities.RoomType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RoomTypeModel {
    Connection connection;
    public List<RoomType> getAllTypes() {
        List<RoomType> roomTypes = new ArrayList<>();
        connection = DbConnection.getDatabaseConnection().getConnection();
        String sql = "SELECT id, typeOfRoom FROM typesOfRooms";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            roomTypes = StreamSupport.stream(
                            new Spliterators.AbstractSpliterator<ResultSet>(Long.MAX_VALUE, Spliterator.ORDERED) {
                                @Override
                                public boolean tryAdvance(Consumer<? super ResultSet> action) {
                                    try {
                                        if (!rs.next()) return false;
                                        action.accept(rs);
                                        return true;
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }, false)
                    .map(resultSet -> {
                        try {
                            return new RoomType(resultSet.getInt("id"), resultSet.getString("typeOfRoom"));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return roomTypes;
    }
}
