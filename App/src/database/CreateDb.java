package database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * The {@code CreateDb} class is responsible for managing the database schema for the application.
 * It provides methods to create, delete, and manage tables within the database.
 *
 * This class depends on the {@link DbConnection} class to get database connections.
 */
public class CreateDb {
    Connection connection;
    public CreateDb()
    {
        connection = DbConnection.getDatabaseConnection().getConnection();
    }
    /**
     * Creates the 'users' table in the database if it does not already exist. This table is used to store user information.
     * @throws SQLException if there is a problem executing the SQL query
     */
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
    /**
     * Creates the 'apartments' table in the database. This table stores information about apartments.
     * @throws SQLException if there is a problem executing the SQL query
     */
    public void createTableApartments() throws SQLException {
        String queryCreateTable = "CREATE TABLE IF NOT EXISTS apartments ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "title TEXT NOT NULL,"
                + "description TEXT NOT NULL,"
                + "idUser INTEGER NOT NULL,"
                + "isPublic BOOL NOT NULL,"
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + "numberOfVotes INTEGER NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryCreateTable);

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
    /**
     * Deletes the 'apartments' table from the database if it exists.
     * @throws SQLException if there is a problem executing the SQL query
     */
    public void deleteTableApartments() throws SQLException {
        String queryDeleteTable = "DROP TABLE IF EXISTS apartments";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryDeleteTable);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
    /**
     * Deletes the 'typesOfFurnitures' table from the database if it exists.
     * @throws SQLException if there is an error executing the SQL command
     */
    public void deleteTableTypesOfFurniture() throws SQLException {
        String queryDeleteTable = "DROP TABLE IF EXISTS typesOfFurnitures";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryDeleteTable);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
    /**
     * Creates the 'typesOfRooms' table in the database, which includes a predefined list of room types.
     * This method might interact with the {@link FillDb} class to populate the table with initial data.
     * @throws SQLException if there is a problem executing the SQL query
     */
    public void createTableTypesOfRooms() throws SQLException {
        String queryCreateTable = "CREATE TABLE IF NOT EXISTS typesOfRooms ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "typeOfRoom TEXT UNIQUE NOT NULL,"
                + "image TEXT NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryCreateTable);

        } catch (SQLException se) {
            System.out.println(se.getMessage());

        }
        //FillDb fillDb=new FillDb();
        //fillDb.insertTypesOfRooms();
    }
    /**
     * Creates the 'rooms' table in the database for storing information about rooms in apartments.
     * @throws SQLException if there is a problem executing the SQL query
     */
    public void createTableRooms() throws SQLException {
        String queryCreateTable = "CREATE TABLE IF NOT EXISTS rooms ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "title TEXT NOT NULL,"
                + "idTypeOfRoom INTEGER NOT NULL,"
                + "idApartment INTEGER NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryCreateTable);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
    /**
     * Deletes the 'rooms' table from the database if it exists.
     * @throws SQLException if there is an error executing the SQL command
     */
    public void deleteTableRooms() throws SQLException {
        String queryDeleteTable = "DROP TABLE IF EXISTS rooms";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryDeleteTable);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
    /**
     * Creates the 'furnitures' table in the database if it does not exist. This table stores information about various furniture items.
     * @throws SQLException if there is an error executing the SQL command
     */
    public void createTableFurnitures() throws SQLException {
        String queryCreateTable = "CREATE TABLE IF NOT EXISTS furnitures ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "title TEXT NOT NULL,"
                + "description TEXT NOT NULL,"
                + "image TEXT NOT NULL,"
                + "width FLOAT NOT NULL,"
                + "height FLOAT NOT NULL,"
                + "color TEXT NOT NULL,"
                + "material TEXT NOT NULL,"
                + "idFurnitureType INTEGER NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryCreateTable);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
    /**
     * Deletes the 'furnitures' table from the database if it exists.
     * @throws SQLException if there is an error executing the SQL command
     */
    public void deleteTableFurnitures() throws SQLException {
        String queryDeleteTable = "DROP TABLE IF EXISTS furnitures";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryDeleteTable);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
    /**
     * Creates the 'typesOfFurnitures' table in the database if it does not exist. This table stores predefined types of furniture.
     *  This method might interact with the {@link FillDb} class to populate the table with initial data.
     *  @throws SQLException if there is an error executing the SQL command
     */
    public void createTableTypesOfFurniture() throws SQLException {
        String queryCreateTable = "CREATE TABLE IF NOT EXISTS typesOfFurnitures ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "typeOfFurniture TEXT UNIQUE NOT NULL,"
                + "image TEXT NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryCreateTable);

        } catch (SQLException se) {
            System.out.println(se.getMessage());

        }
        //FillDb fillDb=new FillDb();
        //fillDb.insertTypesOfFurnitures();
    }
    /**
     * Deletes the 'typesOfFurnitures' table from the database if it exists.
     * This method might interact with the {@link FillDb} class to populate the table with initial data.
     * @throws SQLException if there is an error executing the SQL command
     */
    public void createTableCreatedRooms() throws SQLException {
        String queryCreateTable = "CREATE TABLE IF NOT EXISTS createdRooms ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "typeOfFurniture TEXT UNIQUE NOT NULL,"
                + "image TEXT NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryCreateTable);

        } catch (SQLException se) {
            System.out.println(se.getMessage());

        }
        //FillDb fillDb=new FillDb();
        //fillDb.insertTypesOfFurnitures();
    }
    /**
     * Creates the 'addedFurniture' table in the database if it does not exist. This table stores mappings of which furniture items are added to which rooms.
     * @throws SQLException if there is an error executing the SQL command
     */
    public void createTableAddedFurnitureInRoom() throws SQLException {
        String queryCreateTable = "CREATE TABLE IF NOT EXISTS addedFurniture ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "idFurniture INTEGER NOT NULL,"
                + "idRoom INTEGER NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryCreateTable);

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
    /**
     * Deletes the 'addedFurniture' table from the database if it exists.
     * @throws SQLException if there is an error executing the SQL command
     */
    public void deleteTableAddedFurniture() throws SQLException {
        String queryDeleteTable = "DROP TABLE IF EXISTS addedFurniture";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryDeleteTable);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
    /**
     * Creates the 'assessmentOfApartments' table in the database if it does not exist.
     * This table stores assessments or ratings for apartments.
     * @throws SQLException if there is an error executing the SQL command
     */
    public void createTableAssessmentOfApartments() throws SQLException {
        String queryCreateTable = "CREATE TABLE IF NOT EXISTS assessmentOfApartments ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "idApartment INTEGER NOT NULL,"
                + "assessment INTEGER NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryCreateTable);

        } catch (SQLException se) {
            System.out.println(se.getMessage());

        }
    }
    /**
     * Creates the 'usersVote' table in the database if it does not exist. This table records votes or ratings by users on apartments.
     * @throws SQLException if there is an error executing the SQL command
     */
    public void createTableUsersVotes() throws SQLException {
        String queryCreateTable = "CREATE TABLE IF NOT EXISTS usersVote ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "idUser INTEGER NOT NULL,"
                + "idApartment INTEGER NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryCreateTable);

        } catch (SQLException se) {
            System.out.println(se.getMessage());

        }
    }
    /**
     * Creates the 'notifications' table in the database if it does not exist. This table stores notifications for users concerning apartments.
     * @throws SQLException if there is an error executing the SQL command
     */
    public void createTableNotification() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String queryCreateTable = "CREATE TABLE IF NOT EXISTS notifications ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "idUser INTEGER NOT NULL,"
                    + "idApartment INTEGER NOT NULL,"
                    + "message TEXT NOT NULL)";

            statement.executeUpdate(queryCreateTable);
            statement.close();

        }catch (SQLException se)
        {
            System.out.println(se.getMessage());
        }

    }
}
