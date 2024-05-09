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

public class CreateSettingsModeController extends AbstractController implements ModeControllerInterface {
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

    /**
     * Fills the user interface component with a list of room types retrieved from the database.
     * This method initializes a {@link RoomTypeModel} to fetch all available room types from the database,
     * creates an {@link ObservableList} from the retrieved room types, and sets this list as the items
     * for the {@code roomsTypesList} user interface component.
     */
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
            mainPane.setCenter(parent);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Handles the event when an item is clicked in the user interface.
     * This method creates a new {@link Room} object based on the selected room type from the {@code roomsTypesList}.
     * If a room of the selected type already exists in the {@code usersRoomsList}, a unique name is generated
     * for the new room by appending a numerical suffix. The new room is then added to the {@code usersRoomsList}.
     *
     * @param mouseEvent the {@link MouseEvent} representing the mouse click event that triggered this method.
     */
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
    /**
     * Handles the event when an item is double-clicked in the user interface.
     * If the mouse event represents a double click (i.e., {@code getClickCount() == 2}),
     * the method removes the selected room from the {@code usersRoomsList} and clears the {@code nameOfRoomField}.
     * If the mouse event represents a single click (i.e., {@code getClickCount() == 1}),
     * the method updates the {@code nameOfRoomField} with the title of the selected room for potential editing.
     *
     * @param mouseEvent the {@link MouseEvent} representing the mouse click event that triggered this method.
     */
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
    /**
     * Renames the selected room with the new name provided in the {@code nameOfRoomField}.
     * If the new name is already assigned to another room, an error message is displayed.
     *
     * @param actionEvent the {@link ActionEvent} representing the action event that triggered this method.
     */
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
    /**
     * Creates a new apartment based on the information provided in the user interface fields.
     * If the apartment name is not provided or already exists, appropriate error messages are displayed.
     *
     * @param actionEvent the {@link ActionEvent} representing the action event that triggered this method.
     * @throws ApartmentNotAddedException if an error occurs while adding the apartment to the database.
     * @throws RoomNotAddedException if an error occurs while adding a room to the database.
     */
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
                apartment.setInheritId(user.getId());
                apartment.setDescription(descriptionField.getText());
                apartment.setIsPublic(ispublic.isSelected());
                apartment= apartmentModel.addApartment(apartment);
                roomModel = new RoomModel();
                for(Room room: usersRoomsList.getItems())
                {
                    room.setInheritId(apartment.getId());
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
