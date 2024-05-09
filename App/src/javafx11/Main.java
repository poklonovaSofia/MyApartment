
package javafx11;
import com.fasterxml.jackson.core.type.TypeReference;
import database.CreateDb;
import database.FillDb;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Furniture;
import entities.FurnitureType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Main class of the program, extending {@link Application}.
 * Responsible for launching and initializing the application.
 */
public class Main extends Application {
    private Stage primaryStage;

    private Scene mainScene;
    private Stack<Parent> previousPages = new Stack<>();
    /**
     * The start method is called upon program startup and initializes the main window.
     * @param stage the {@link Stage} object representing the main window of the program
     * @throws IOException if an error occurs while loading the FXML file
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * The initialize method is called to initialize the application.
     * It creates the database and launches the main application window.
     * @param args the command line arguments
     * @throws SQLException if an error occurs while working with the database
     */
    public void initialize(String[] args) throws SQLException {
        createDatabase();
        launchApplication(args);
    }
    /**
     * The createDatabase method initializes the database structure by creating necessary tables
     * and imports initial data from a JSON file to populate the Furniture table.
     *
     * This method uses the {@link CreateDb} class to handle the creation of database tables.
     * It also potentially uses the {@link Furniture} class for populating the database with furniture
     * information from a '.json' file, although this functionality is currently commented out.
     *
     * @throws SQLException if an error occurs while working with the database.
     */
    private void createDatabase() throws SQLException {
        CreateDb createDb = new CreateDb();
        createDb.createTableUsers();
        //createDb.deleteTableRooms();
        //createDb.deleteTableApartments();
        createDb.createTableApartments();
        createDb.createTableTypesOfRooms();
        createDb.createTableRooms();
        //createDb.deleteTableFurnitures();
        createDb.createTableFurnitures();
        //createDb.deleteTableTypesOfFurniture();
        createDb.createTableTypesOfFurniture();
        //createDb.deleteTableAddedFurniture();
        //createDb.deleteTableAddedFurniture();
        createDb.createTableAddedFurnitureInRoom();
        createDb.createTableUsersVotes();
        createDb.createTableNotification();

       /* List<Furniture> furnitureList = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            furnitureList = objectMapper.readValue(new File("src/images/furniture.json"), new TypeReference<List<Furniture>>() {});

        } catch (IOException e) {
            e.printStackTrace();
        }

        FillDb fillDb = new FillDb();
        fillDb.insertFurnitures(furnitureList);*/
    }
    /**
     * The launchApplication method launches the main application window.
     * @param args the command line arguments
     */
    private void launchApplication(String[] args) {
        launch(args);
    }
    /**
     * The main method is the entry point of the program.
     * It creates an instance of the Main class and calls the initialize method to initialize the program.
     * @param args the command line arguments
     * @throws SQLException if an error occurs while working with the database
     */
    public static void main(String[] args) throws SQLException {
        Main app = new Main();
        app.initialize(args);
    }
}