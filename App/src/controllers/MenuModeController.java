package controllers;

import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import utils.ModeControllerInterface;

import java.io.IOException;
/**
 * Controller class responsible for managing the main menu interface of the application.
 * This class handles user interactions with menu panes, loads corresponding scenes,
 * and provides access to user information. It extends {@link AbstractController}
 * to inherit common controller functionalities.
 */
public class MenuModeController extends AbstractController{

    @FXML
    private BorderPane mainPane;
    @FXML
    public void clickedOnCreatePane(MouseEvent mouseEvent) {
        loadScene("/views/CreateSettingsMode.fxml");
    }
    public User getUser(){return user;}

    protected void loadScene(String fxml)
    {
        Parent parent;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            parent = loader.load();
            if(user!=null) {
                ModeControllerInterface controller = loader.getController();
                controller.setUser(user);
                controller.fill();
            }

            mainPane.setCenter(parent);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void clickedOnMyWorksPane(MouseEvent mouseEvent) {
        loadScene("/views/MyWorksMode.fxml");
    }

    public void clickedOnVotePane(MouseEvent mouseEvent) {
        loadScene("/views/VoteMode.fxml");
    }

    public void clickedOnStatisticsPane(MouseEvent mouseEvent) {
        loadScene("/views/StatisticsMode.fxml");
    }

    public void clickedOnMyProfile(MouseEvent mouseEvent) {
        loadScene("/views/MyProfile.fxml");
    }
}
