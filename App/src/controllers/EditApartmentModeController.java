package controllers;

import components.TabControl;
import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.*;

import java.io.IOException;
import java.util.List;

public class EditApartmentModeController extends AbstractController {
    private FurnitureTypeModel furnitureTypeModel;
    private FurnitureModel furnitureModel;
    private List<FurnitureType> furnitureTypeList;

    private ApartmentModel apartmentModel;
    private RoomModel roomModel;
    private Apartment apartment;
    @FXML
    private TabPane mainTabs;

    public void setApartment(Apartment apartment) {
        this.apartment =apartment;
    }
    @FXML
    private void initialize() throws IOException {

    }
    public void fill() throws IOException {
        furnitureTypeModel = new FurnitureTypeModel();
        furnitureTypeList =furnitureTypeModel.getAllTypes();
        for(Room room: apartment.getRooms())
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TabControl.fxml"));
            Node content = loader.load();

            TabControl controller = loader.getController();
            controller.setRoom(room);
            content.setUserData(controller);
            controller.fillTreeFurniture(furnitureTypeList);
            Tab t = new Tab();
            t.setText(room.getTitle());
            t.setContent(content);

            mainTabs.getTabs().add(t);
        }
    }

    public void returnBack(ActionEvent actionEvent) {
        loadScene("/views/MenuMode.fxml", user);
    }



    /**
     * Saves changes made to the apartment and its associated furniture.
     *
     * @param actionEvent the {@link ActionEvent} representing the action event that triggered this method.
     */
    public void saveApartment(ActionEvent actionEvent) {
        apartmentModel=new ApartmentModel();
        furnitureModel = new FurnitureModel();
        apartmentModel.updateApartment(apartment.getId());

        for (Tab tab : mainTabs.getTabs()) {
            AnchorPane content = (AnchorPane) tab.getContent();
            Object userData = content.getUserData();
            if (userData instanceof TabControl) {

                TabControl controller = (TabControl) userData;
                List<Furniture> furnList = controller.getListFurniture();
                for(Furniture f: furnList)
                {
                    furnitureModel.addFurniture(f.getId(), controller.getRoom().getId());
                }
            }
        }
        returnBack(new ActionEvent());

    }
}
