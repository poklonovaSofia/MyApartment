package components;

import entities.Furniture;
import entities.FurnitureType;
import entities.Room;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.FurnitureTypeModel;

import java.io.IOException;
import java.util.List;

public class TabControl extends Tab {
    Room room;
    private FurnitureTypeModel furnitureTypeModel;
    @FXML
    private TreeView treeFurniture;
    @FXML
    private ListView<Furniture> listAddedFurniture;
    public List<Furniture> getListFurniture()
    {
        return listAddedFurniture.getItems();
    }
    public void setRoom(Room room) {
        this.room=room;
    }
    public Room getRoom() {
        return room;
    }
    @FXML
    private AnchorPane paneForFurniture;

    public void fillTreeFurniture(List<FurnitureType> allTypes) {
        TreeItem<String> rootItem = new TreeItem<>("Furniture");
        treeFurniture.setRoot(rootItem);
        for(FurnitureType ft: allTypes)
        {
            TreeItem<String> item = new TreeItem<>(ft.getType());
            treeFurniture.getRoot().getChildren().add(item);
        }
    }

    public void onTreeItemClicked(MouseEvent Event) throws IOException {
        TreeItem<String> item = (TreeItem<String>) treeFurniture.getSelectionModel().getSelectedItem();
        if (item != null && !(item.getValue().equals("Furniture"))) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PaneForFurniture.fxml"));
            Node content = loader.load();
            PaneForFurnitureControl controller = loader.getController();
            controller.setTypeOfFurniture(item.getValue());
            controller.setPaneListener(furniture -> {
                listAddedFurniture.getItems().add(furniture);
                listAddedFurniture.refresh();
            });
            controller.fillAnchor();
            paneForFurniture.getChildren().add(content);

        }
    }

    public void deleteFurniture(MouseEvent mouseEvent) {
        Furniture selectedFurniture = listAddedFurniture.getSelectionModel().getSelectedItem();
        if (mouseEvent.getClickCount() == 2) {

            if (selectedFurniture != null) {
                listAddedFurniture.getItems().remove(selectedFurniture);
                listAddedFurniture.refresh();
            }
        }
    }
}
