package controllers;

import entities.FurnitureType;
import entities.Notification;
import entities.RoomType;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import models.NotificationModel;
import models.UserModel;
import utils.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

public class MyProfileController extends AbstractController implements ModeControllerInterface {
    @FXML
    private Label labelErrorUserName;
    @FXML
    private Label labelErrorEmail;

    private NotificationModel notificationModel;
    private UserModel userModel;

    @FXML
    private TextField fieldUsername;
    @FXML
    private TextField fieldEmail;
    @FXML
    private ListView<Notification> listViewOfNotifications;
    /**
     * Fills the user profile interface with user information and notifications.
     * This method sets the username and email fields with the current user's information.
     * It also retrieves all notifications for the user from the database using {@link NotificationModel}
     */
    public void fill() {
        fieldUsername.setText(user.getUsername());
        fieldEmail.setText(user.getEmail());
        notificationModel=new NotificationModel();
        ObservableList<Notification> observableList = FXCollections.observableArrayList(notificationModel.getAllNotificationsForUser(user.getId()));

        listViewOfNotifications.setItems(observableList);
        listViewOfNotifications.refresh();
    }



    public void returnToHome(ActionEvent actionEvent) {
        loadScene("/views/MenuMode.fxml", user);
    }


    public void logout(ActionEvent actionEvent) {
        loadScene("/views/Home.fxml", null);
    }
    /**
     * Saves the user settings changes.
     * This method retrieves the new username and email entered by the user in the text fields.
     * It validates the new username and email using {@link UsernameValidator} and {@link EmailValidator} respectively.
     * If the validation passes, it updates the user's username and email in the database using {@link UserModel}.
     * Any error messages encountered during validation or updating are displayed on corresponding error labels.
     *
     * @param actionEvent the {@link ActionEvent} representing the action event that triggered this method.
     */
    public void saveSettings(ActionEvent actionEvent) {
        userModel = new UserModel();
        labelErrorUserName.setText("");
        labelErrorEmail.setText("");
        if(user.getUsername() != fieldUsername.getText())
        {
            try {
                UsernameValidator usernameValidator = new UsernameValidator();

                boolean isUsernameValid = false;
                isUsernameValid =usernameValidator.validate(fieldUsername.getText());
                if (isUsernameValid) {
                    try {
                        userModel.updateUsername(user.getId(), fieldUsername.getText());
                        user.setUsername(fieldUsername.getText());
                    }catch(UserNotUpdated unu)
                    {
                        labelErrorUserName.setText(unu.getMessage());
                    }
                }
            }catch(UsernameException ue)
            {
                labelErrorUserName.setText(ue.getMessage());
            }
        }
        if(user.getEmail() != fieldEmail.getText())
        {
            try {
                EmailValidator emailValidator = new EmailValidator();

                boolean isEmailValid = false;
                isEmailValid =emailValidator.validate(fieldEmail.getText());
                if (isEmailValid) {
                    try {
                        userModel.updateEmail(user.getId(), fieldEmail.getText());
                        user.setEmail(fieldEmail.getText());
                    }catch(UserNotUpdated unu)
                    {
                        labelErrorEmail.setText(unu.getMessage());

                    }
                }
            }catch(EmailException ue) {
                labelErrorEmail.setText(ue.getMessage());
            }


        }
    }
}
