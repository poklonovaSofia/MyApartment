package controllers;

import entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import models.FurnitureModel;
import models.RoomModel;
import utils.ModeControllerInterface;

import java.io.IOException;

public class ShowWorkModeController extends AbstractController implements ModeControllerInterface {

    @FXML
    private Label nameOfSubject;
    private RoomModel roomModel;
    private FurnitureModel furnitureModel;

    private Apartment apartment;
    @FXML
    private TreeView apartmentTree;
    /**
     * Fills the interface with the details of the current apartment.
     * This method retrieves all rooms associated with the apartment from the database using {@link RoomModel}.
     * It also retrieves all furniture associated with each room using {@link FurnitureModel}.
     * The method sets the name of the subject (apartment) in the interface.
     * It then populates a tree view (apartmentTree) with the rooms and their associated furniture.
     * Each room is represented as a parent node, and its associated furniture items are children nodes.
     * If an IOException occurs during loading, it throws a RuntimeException.
     */
    public void fill() {
        roomModel = new RoomModel();
        furnitureModel = new FurnitureModel();
        apartment.setRooms(roomModel.getAllRoomsByIdAp(apartment.getId()));
        nameOfSubject.setText(apartment.getTitle());
        TreeItem<String> rootItem = new TreeItem<>(apartment.getTitle());
        apartmentTree.setRoot(rootItem);
        for(Room ft: apartment.getRooms())
        {
            ft.setFurnitureList(furnitureModel.getAllFurnitureById(ft.getId()));
            TreeItem<String> item = new TreeItem<>(ft.getTitle());
            apartmentTree.getRoot().getChildren().add(item);
            for (Furniture f : ft.getFurnitureList()) {
                TreeItem<String> itemModel = new TreeItem<>(f.toString());
                item.getChildren().add(itemModel);
            }
        }
    }


    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public void returnToHome(ActionEvent actionEvent) {
        loadScene("/views/MyWorksMode.fxml", user);
    }
    @Override
    protected void loadScene(String fxml, User user)
    {
        Parent parent;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            parent = loader.load();

            if(user!=null) {
                ModeControllerInterface controller = loader.getController();
                controller.setUser(user);
                controller.fill();
            }
            mainPane.setCenter(parent);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
