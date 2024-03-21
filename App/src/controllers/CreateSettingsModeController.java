package controllers;

import entities.Room;
import entities.RoomType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.RoomTypeModel;

import java.io.IOException;
import java.util.Objects;

public class CreateSettingsModeController extends MenuModeController{
    private RoomTypeModel roomTypeModel;
    @FXML
    private BorderPane mainPane;

    @FXML
    private ListView<RoomType> roomsTypesList;
    @FXML
    private ListView<Room> usersRoomsList;
    @FXML
    private void initialize() {
        roomTypeModel = new RoomTypeModel();
        ObservableList<RoomType> observableRoomTypes = FXCollections.observableArrayList(roomTypeModel.getAllTypes());
        roomsTypesList.setItems(observableRoomTypes);
    }
    @FXML
    public void clickedCancel(ActionEvent actionEvent) {
        loadScene("/views/MenuMode.fxml");
    }
    private void loadScene(String fxml)
    {

        Parent parent;
        try{
            parent = FXMLLoader.load(getClass().getResource(fxml));
            mainPane.setCenter(parent);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    public void onItemClicked(MouseEvent mouseEvent) {
        Room newRoom = new Room();

        RoomType selectedRoomType = roomsTypesList.getSelectionModel().getSelectedItem();
        if (selectedRoomType != null) {
            boolean roomExists = false;
            for (Room room : usersRoomsList.getItems()) {
                if(Objects.equals(room.getTitle(), selectedRoomType.getType()))
                {
                    roomExists =true;
                    break;
                }
            }
            if (roomExists) {

                int count = 1;
                String newRoomName = selectedRoomType.getType() + " (" + count + ")";
                while (roomExists) {
                    roomExists = false;
                    for (Room room : usersRoomsList.getItems()) {
                        if (Objects.equals(room.getTitle(), newRoomName)) {
                            roomExists = true;
                            count++;
                            newRoomName = selectedRoomType.getType() + " (" + count + ")";
                            break;
                        }
                    }
                }
                newRoom.setTitle(newRoomName);
            }
            else{
                newRoom.setTitle(selectedRoomType.getType());
            }
            newRoom.setIdType(selectedRoomType.getId());
            usersRoomsList.getItems().add(newRoom);

        }


    }

    public void ItemDoubleClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            Room selectedRoom = usersRoomsList.getSelectionModel().getSelectedItem();
            if (selectedRoom != null) {
                usersRoomsList.getItems().remove(selectedRoom);
            }
        }
    }
}
