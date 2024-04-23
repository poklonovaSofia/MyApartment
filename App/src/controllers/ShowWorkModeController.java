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

public class ShowWorkModeController implements ModeControllerInterface {

    @FXML
    private Label nameOfSubject;
    private RoomModel roomModel;
    private FurnitureModel furnitureModel;
    private User user;
    private Apartment apartment;
    @FXML
    private TreeView apartmentTree;
    @FXML
    private BorderPane mainPane;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public void returnToHome(ActionEvent actionEvent) {
        loadScene("/views/MyWorksMode.fxml", user);
    }
    private void loadScene(String fxml, User user)
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
