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

public class SignInController extends AbstractController implements Initializable {
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
    public void changeToSignUp(ActionEvent actionEvent) {
        loadScene("/views/SignUp.fxml", null);
    }
    @Override
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

    @FXML
    public void returnHome(ActionEvent actionEvent) {
        loadScene("/views/Home.fxml", null);
    }
    public void changeToMenuMode(User user) {
        loadScene("/views/MenuMode.fxml", user);
    }
    /**
     * Handles the sign-in process for the user.
     * This method retrieves the user's input email and password from the corresponding text fields.
     * It then attempts to find a user with the provided email and password using {@link UserModel}.
     * If a user is found, it transitions to the menu mode interface, passing the user object to the next scene.
     * If no user is found, it displays an error message indicating incorrect email or password.
     *
     * @param actionEvent the {@link ActionEvent} representing the action event that triggered this method.
     */
    public void signIn(ActionEvent actionEvent) {
        userModel = new UserModel();
        User user=userModel.findUser(new User(fieldEmail.getText(), fieldPassword.getText(), ""));
        if(user != null)
        {
            changeToMenuMode(user);
        }
        else {
            labelErrorMessage.setText("Incorrect email or password");
        }
    }
}
