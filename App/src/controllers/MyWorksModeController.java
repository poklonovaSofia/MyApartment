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

public class MyWorksModeController extends AbstractController implements ModeControllerInterface {

    private Apartment apartment;
    private ApartmentModel apartmentModel;


    @FXML
    private ListView<AnchorPane> listViewApartments;
    public void returnBack(ActionEvent actionEvent) {
        loadScene("/views/MenuMode.fxml", user);
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
    /**
     * Fills the user profile interface with the user's apartments.
     * This method retrieves all apartments associated with the user from the database using {@link ApartmentModel}.
     * For each apartment, it loads the corresponding PostControl FXML file and sets its controller properties.
     * It then adds the loaded content (AnchorPane) to the listViewApartments.
     * Additionally, it sets up a callback function for showing the details of a selected apartment.
     * If an IOException occurs during loading, it throws a RuntimeException.
     */
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
