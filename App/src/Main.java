

import database.CreateDb;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
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
        createDb.createTableApartments();
        createDb.createTableTypesOfRooms();
    }
    private void launchApplication(String[] args) {
        launch(args);
    }

    public static void main(String[] args) throws SQLException {
        Main app = new Main();
        app.initialize(args);
    }
}