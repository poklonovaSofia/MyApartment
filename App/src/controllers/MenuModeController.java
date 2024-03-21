package controllers;

import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MenuModeController {
    private User user;
    @FXML
    private BorderPane mainPane;
    @FXML
    public void clickedOnCreatePane(MouseEvent mouseEvent) {
        loadScene("/views/CreateSettingsMode.fxml");
    }
    public void setUser(User user){this.user=user;}
    public User getUser(){return user;}
    private void loadScene(String fxml)
    {
        Parent parent;
        try{
            parent = FXMLLoader.load(getClass().getResource(fxml));
            mainPane.setCenter(parent);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
