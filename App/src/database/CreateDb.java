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
    public void createTableApartments() throws SQLException {
        String queryCreateTable = "CREATE TABLE IF NOT EXISTS apartments ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "title TEXT UNIQUE NOT NULL,"
                + "description TEXT NOT NULL,"
                + "idUser INTEGER NOT NULL,"
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryCreateTable);

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
    public void createTableTypesOfRooms() throws SQLException {
        String queryCreateTable = "CREATE TABLE IF NOT EXISTS typesOfRooms ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "typeOfRoom TEXT UNIQUE NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryCreateTable);

        } catch (SQLException se) {
            System.out.println(se.getMessage());

        }
        //FillDb fillDb=new FillDb();
        //fillDb.insertTypesOfRooms();

    }
}
