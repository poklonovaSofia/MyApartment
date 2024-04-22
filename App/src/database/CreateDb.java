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
    public void deleteTableApartments() throws SQLException {
        String queryDeleteTable = "DROP TABLE IF EXISTS apartments";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryDeleteTable);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
    public void deleteTableTypesOfFurniture() throws SQLException {
        String queryDeleteTable = "DROP TABLE IF EXISTS typesOfFurnitures";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryDeleteTable);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
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
    public void deleteTableRooms() throws SQLException {
        String queryDeleteTable = "DROP TABLE IF EXISTS rooms";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryDeleteTable);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
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
    public void deleteTableFurnitures() throws SQLException {
        String queryDeleteTable = "DROP TABLE IF EXISTS furnitures";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryDeleteTable);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
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
    public void deleteTableAddedFurniture() throws SQLException {
        String queryDeleteTable = "DROP TABLE IF EXISTS addedFurniture";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryDeleteTable);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
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
