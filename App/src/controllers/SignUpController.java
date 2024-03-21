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
import java.sql.Connection;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    private UserModel userModel;
    @FXML
    private Label labelErrorDataBase;
    @FXML
    private Label labelErrorUsername;
    @FXML
    private Label labelErrorEmail;
    @FXML
    private Label labelErrorPassword;
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private Hyperlink linkToReturn;

    @FXML
    private BorderPane mainPane;
    @FXML
    private TextField fieldUsername;
    @FXML
    private TextField fieldEmail;
    @FXML
    private TextField fieldPassword;

    @FXML
    public void changeToSignIn(ActionEvent ae) {
        loadScene("/views/SignIn.fxml", null);
    }
    public void changeToMenuMode(User user) {
        loadScene("/views/MenuMode.fxml", user);
    }
    private void loadScene(String fxml, User user)
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
    public void returnHome(ActionEvent ae) {
        loadScene("/views/Home.fxml", null);
    }
    @FXML
    public void signUp(ActionEvent ae) throws PasswordException, EmailException, UsernameException {
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        UsernameValidator usernameValidator = new UsernameValidator();
        userModel = new UserModel();
        boolean isEmail=false, isPass=false, isUN=false, isUserAdded = false;

        try {
            if (emailValidator.validate(fieldEmail.getText())) {
                labelErrorEmail.setText("");
                isEmail =true;
            }
        } catch (EmailException e) {

            labelErrorEmail.setText(e.getMessage());
        }

        try {
            if (passwordValidator.validate(fieldPassword.getText())) {
                labelErrorPassword.setText("");
                isPass = true;
            }
        } catch (PasswordException e) {
            labelErrorPassword.setText(e.getMessage());
        }
        try {
            if (usernameValidator.validate(fieldUsername.getText())) {
                labelErrorUsername.setText("");
                isUN = true;
            }
        } catch (UsernameException e) {
            labelErrorUsername.setText(e.getMessage());
        }
        if(isEmail && isPass && isUN)
        {
            User user = new User( fieldEmail.getText(), fieldPassword.getText(), fieldUsername.getText());
            if(userModel.getIdUserByUsernameEmail(user)==0)
            {
                try {
                    user = userModel.addUser(user);
                    changeToMenuMode(user);
                }catch(UserNotAddedException ue) {
                    labelErrorDataBase.setText(ue.getMessage());
                }

            }

        }
            //if no
            //insert User in db
            //go to userview
            //if yes
            //throw

    }
}



