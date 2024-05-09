package controllers;

import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AbstractController {
    protected User user;
    public void setUser(User user){this.user = user;}

    @FXML
    public BorderPane mainPane;
    protected void loadScene(String fxml, User user)
    {

        Parent parent;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            parent = loader.load();
            if(user!=null) {
                MenuModeController controller = loader.getController();
                controller.setUser(user);

            }
            mainPane.setCenter(parent);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
