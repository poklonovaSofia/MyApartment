package database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDb {
    Connection connection;
    public CreateDb()
    {
        connection = DbConnection.getDatabaseConnection().getConnection();
    }

    public void createTableUsers() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String queryCreateTable = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "username TEXT UNIQUE NOT NULL,"
                    + "password TEXT NOT NULL,"
                    + "email TEXT UNIQUE NOT NULL)";

            statement.executeUpdate(queryCreateTable);
            statement.close();

        }catch (SQLException se)
        {
            System.out.println(se.getMessage());
        }

    }
}
