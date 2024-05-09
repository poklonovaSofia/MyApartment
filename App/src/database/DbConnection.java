package database;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * The DbConnection class represents a singleton connection to the SQLite database.
 */
public class DbConnection {
    private Connection con;
    private static DbConnection dbc;
    /**
     * Constructs a new DbConnection object and establishes a connection to the SQLite database.
     */
    private DbConnection() {
        try {

            con = DriverManager.getConnection("jdbc:sqlite:myApartment.db");
        } catch ( SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Returns the singleton instance of the DbConnection object.
     * If the instance does not exist, it creates a new one.
     * @return the singleton instance of DbConnection
     */
    public static DbConnection getDatabaseConnection() {
        if (dbc == null) {
            dbc = new DbConnection();
        }
        return dbc;
    }
    /**
     * Returns the connection to the database.
     * @return the Connection object representing the database connection
     */
    public Connection getConnection() {
        return con;
    }

    /**
     * The main method of the DbConnection class.
     * It initializes a new DbConnection object, establishing a connection to the database.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        new DbConnection();
    }
}
