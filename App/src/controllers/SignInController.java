package controllers;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import models.UserModel;
import utils.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    private UserModel userModel;
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private TextField fieldEmail;
    @FXML
    private TextField fieldPassword;
    @FXML
    private Label labelErrorMessage;
    @FXML
    private Hyperlink linkToReturn;
    @FXML
    private BorderPane mainPane;
    @FXML
    public void changeToSignUp(ActionEvent actionEvent) {
        loadScene("/views/SignUp.fxml");
    }

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

    @FXML
    public void returnHome(ActionEvent actionEvent) {
        loadScene("/views/Home.fxml");
    }
    public void changeToMenuMode() {
        loadScene("/views/MenuMode.fxml");
    }
    public void signIn(ActionEvent actionEvent) {
        userModel = new UserModel();
        User user=userModel.findUser(new User(fieldEmail.getText(), fieldPassword.getText(), ""));
        if(user != null)
        {
            changeToMenuMode();
        }





    }
}
