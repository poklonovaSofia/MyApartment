package controllers;

import entities.Apartment;
import entities.Room;
import entities.RoomType;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.ApartmentModel;
import models.RoomModel;
import models.RoomTypeModel;
import utils.ApartmentNotAddedException;
import utils.ModeControllerInterface;
import utils.RoomNotAddedException;

import java.io.IOException;
import java.util.Objects;

public class CreateSettingsModeController extends MenuModeController implements ModeControllerInterface {
    private ApartmentModel apartmentModel;
    private RoomModel roomModel;
    private RoomTypeModel roomTypeModel;
    @FXML
    private CheckBox ispublic;
    @FXML
    private BorderPane mainPane;
    @FXML
    private TextField nameOfApartmentField;
    @FXML
    private ListView<RoomType> roomsTypesList;
    @FXML
    private ListView<Room> usersRoomsList;
    private String oldRoomName;
    @FXML
    private TextField nameOfRoomField;
    @FXML
    private Label labelExistApartmentName;
    @FXML
    private Label labelErrorRoomName;
    @FXML
    private TextArea descriptionField;
    private User user;
    public void setUser(User user){this.user = user;}

    public void fill() {
        roomTypeModel = new RoomTypeModel();
        ObservableList<RoomType> observableRoomTypes = FXCollections.observableArrayList(roomTypeModel.getAllTypes());
        roomsTypesList.setItems(observableRoomTypes);
    }
    @FXML
    public void clickedCancel(ActionEvent actionEvent) {
        loadScene("/views/MenuMode.fxml", user);
    }
    private void loadScene(String fxml, Apartment apartment)
    {

        Parent parent;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            parent = loader.load();
            if(apartment!=null) {
                EditApartmentModeController controller = loader.getController();
                if(user != null)
                    controller.setUser(user);
                controller.setApartment(apartment);
                controller.fill();
            }
            mainPane.setCenter(parent);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
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
        Room selectedRoom = usersRoomsList.getSelectionModel().getSelectedItem();
        if (mouseEvent.getClickCount() == 2) {

            if (selectedRoom != null) {
                usersRoomsList.getItems().remove(selectedRoom);
                nameOfRoomField.setText("");
            }
        }
        else if(mouseEvent.getClickCount() == 1)
        {

            if (selectedRoom != null) {
                oldRoomName = selectedRoom.getTitle();
                nameOfRoomField.setText(oldRoomName);

            }
        }
    }

    public void renameRoom(ActionEvent actionEvent) {
        boolean isExistNewName =false;
        if(nameOfRoomField.getText().length() !=0)
        {
            for (Room room: usersRoomsList.getItems()) {
                if(Objects.equals(room.getTitle(), nameOfRoomField.getText()))
                {
                    labelErrorRoomName.setText("The name already exists");
                    isExistNewName=true;
                    break;
                }
            }
            if(!isExistNewName)
            {
                for (Room room : usersRoomsList.getItems()) {
                    if(Objects.equals(room.getTitle(), oldRoomName))
                    {
                        room.setTitle(nameOfRoomField.getText());
                        labelErrorRoomName.setText("");
                        nameOfRoomField.setText("");
                        usersRoomsList.refresh();
                        break;
                    }
                }
            }
        }
    }

    public void createApartment(ActionEvent actionEvent) throws ApartmentNotAddedException, RoomNotAddedException {
        if(nameOfApartmentField.getText().length() != 0)
        {
            apartmentModel = new ApartmentModel();
            if(apartmentModel.existUsersApartmentName(nameOfApartmentField.getText(), user.getId()))
            {
                labelExistApartmentName.setText("The name already exists");
            }
            else {
                Apartment apartment = new Apartment();
                apartment.setTitle(nameOfApartmentField.getText());
                apartment.setUserId(user.getId());
                apartment.setDescription(descriptionField.getText());
                apartment.setIsPublic(ispublic.isSelected());
                apartment= apartmentModel.addApartment(apartment);
                roomModel = new RoomModel();
                for(Room room: usersRoomsList.getItems())
                {
                    room.setApartmentId(apartment.getId());
                    room = roomModel.addRoom(room);
                    apartment.getRooms().add(room);
                }
                goToEditApartmentMode(apartment);
            }
        }
        else {
            labelExistApartmentName.setText("The name is empty");
        }
    }

    private void goToEditApartmentMode(Apartment apartment) {
        loadScene("/views/EditApartmentMode.fxml", apartment);
    }


}
