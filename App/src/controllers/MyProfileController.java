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

public class MyProfileController implements ModeControllerInterface {
    @FXML
    private Label labelErrorUserName;
    @FXML
    private Label labelErrorEmail;
    private User user;
    private NotificationModel notificationModel;
    private UserModel userModel;
    @FXML
    private BorderPane mainPane;
    @FXML
    private TextField fieldUsername;
    @FXML
    private TextField fieldEmail;
    @FXML
    private ListView<Notification> listViewOfNotifications;
    public void fill() {
        fieldUsername.setText(user.getUsername());
        fieldEmail.setText(user.getEmail());
        notificationModel=new NotificationModel();
        ObservableList<Notification> observableList = FXCollections.observableArrayList(notificationModel.getAllNotificationsForUser(user.getId()));

        listViewOfNotifications.setItems(observableList);
        listViewOfNotifications.refresh();
    }

    public void setUser(User user) {
        this.user=user;
    }

    public void returnToHome(ActionEvent actionEvent) {
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

    public void logout(ActionEvent actionEvent) {
        loadScene("/views/Home.fxml", null);
    }

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
