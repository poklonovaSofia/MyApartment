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
import java.util.concurrent.*;

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
    public void signUp(ActionEvent ae)  {
        labelErrorPassword.setText("");
        labelErrorUsername.setText("");
        labelErrorEmail.setText("");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        UsernameValidator usernameValidator = new UsernameValidator();
        UserModel userModel = new UserModel();

        Callable<Boolean> emailValidationTask = () -> emailValidator.validate(fieldEmail.getText());
        Callable<Boolean> passwordValidationTask = () -> passwordValidator.validate(fieldPassword.getText());
        Callable<Boolean> usernameValidationTask = () -> usernameValidator.validate(fieldUsername.getText());

        boolean isEmailValid = false;
        boolean isPasswordValid = false;
        boolean isUsernameValid = false;

        try {
            Future<Boolean> usernameFuture = executorService.submit(usernameValidationTask);
            Future<Boolean> emailFuture = executorService.submit(emailValidationTask);
            Future<Boolean> passwordFuture = executorService.submit(passwordValidationTask);


            try {
                isUsernameValid = usernameFuture.get();
                isEmailValid = emailFuture.get();
                isPasswordValid = passwordFuture.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Знову встановлюємо статус перерваного потоку
                // Логування або інша обробка InterruptedException
            } catch (ExecutionException e) {
                Throwable cause = e.getCause(); // Отримуємо справжню причину винятку
                if (cause instanceof PasswordException) {
                    labelErrorPassword.setText(cause.getMessage());
                } else if (cause instanceof EmailException) {
                    labelErrorEmail.setText(cause.getMessage());
                } else if (cause instanceof UsernameException) {
                    labelErrorUsername.setText(cause.getMessage());
                } else {
                    e.printStackTrace();
                }
            }

            if (isEmailValid && isPasswordValid && isUsernameValid) {
                User user = new User(fieldEmail.getText(), fieldPassword.getText(), fieldUsername.getText());
                if (userModel.getIdUserByUsernameEmail(user) == 0) {
                    try {
                        user = userModel.addUser(user);
                        changeToMenuMode(user);
                    } catch (UserNotAddedException ue) {
                        labelErrorDataBase.setText(ue.getMessage());
                    }
                }
            }
        } finally {
            executorService.shutdown();
        }

        //if no
            //insert User in db
            //go to userview
            //if yes
            //throw

    }
}



