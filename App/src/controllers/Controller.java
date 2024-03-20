package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button buttonSignIn;

    @FXML
    private Button buttonSignUp;

    @FXML
    private BorderPane mainPane;
    @FXML
    public void handleButtonClicked(ActionEvent event) {
        if (event.getSource() == buttonSignIn) {
            loadContent("/views/SignIn.fxml");
        } else if (event.getSource() == buttonSignUp) {
            loadContent("/views/SignUp.fxml");
        }
    }
    private void loadContent(String fxml) {
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource(fxml));
            mainPane.setCenter(parent);
            //mainPane.setPrefHeight(800);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}