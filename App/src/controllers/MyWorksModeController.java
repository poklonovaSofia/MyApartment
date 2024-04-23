package controllers;

import components.CardControl;
import components.PostControl;
import entities.Apartment;
import entities.Furniture;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.ApartmentModel;
import utils.ModeControllerInterface;

import java.io.IOException;
import java.util.List;

public class MyWorksModeController implements ModeControllerInterface {
    private User user;
    private Apartment apartment;
    private ApartmentModel apartmentModel;
    public void setUser(User user){this.user=user;}
    @FXML
    private BorderPane mainPane;
    @FXML
    private ListView<AnchorPane> listViewApartments;
    public void returnBack(ActionEvent actionEvent) {
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
            else
            {
                ShowWorkModeController controller = loader.getController();
                controller.setUser(this.user);
                controller.setApartment(apartment);
                controller.fill();

            }
            mainPane.setCenter(parent);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void fill() {
        apartmentModel=new ApartmentModel();
        List<Apartment> apartmentList =apartmentModel.getAllApartByIdUser(user.getId());
        for(Apartment ap: apartmentList)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PostControl.fxml"));
            try {

                AnchorPane content = loader.load();
                PostControl controller = loader.getController();
                controller.set(ap);
                controller.setShowThisPost((apartment)->{
                    this.apartment = apartment;
                    loadScene("/views/ShowWorkMode.fxml", null);
                });
                listViewApartments.getItems().add(content);

            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
