

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

public class Main extends Application {
    private Stage primaryStage;

    private Scene mainScene;
    private Stack<Parent> previousPages = new Stack<>();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public void initialize(String[] args) throws SQLException {
        createDatabase();
        launchApplication(args);
    }

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
        createDb.deleteTableAddedFurniture();
        createDb.createTableAddedFurnitureInRoom();


        /*List<Furniture> furnitureList = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            furnitureList = objectMapper.readValue(new File("src/images/furniture.json"), new TypeReference<List<Furniture>>() {});

        } catch (IOException e) {
            e.printStackTrace();
        }

        FillDb fillDb = new FillDb();
        fillDb.insertFurnitures(furnitureList);*/
    }
    private void launchApplication(String[] args) {
        launch(args);
    }

    public static void main(String[] args) throws SQLException {
        Main app = new Main();
        app.initialize(args);
    }
}