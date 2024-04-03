package components;

import entities.Apartment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import models.ApartmentModel;

import java.util.Optional;


public class PostControl extends AnchorPane {
    private Apartment apartment;
    private ApartmentModel apartmentModel;
    @FXML
    private Label description;
    @FXML
    private Hyperlink title;
    @FXML
    private CheckBox ispublic;
    public void set(Apartment ap) {
        this.apartment=ap;
        description.setText(apartment.getDescription());
        title.setText(apartment.getTitle());
        ispublic.setSelected(apartment.getIsPublic());

    }

    public void changeState(ActionEvent actionEvent) {
        apartmentModel = new ApartmentModel();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Submit");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        if(ispublic.isSelected()) {
            alert.setContentText("Are you sure you want to make this apartment public?");
        }
        else {
            alert.setContentText("Are you sure you want to make this apartment private?");
        }
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            apartment.setIsPublic(ispublic.isSelected());
            apartmentModel.changeStateOfPrivacy(apartment.getId(), apartment.getIsPublic());
        } else {
            if(ispublic.isSelected())
            {
                ispublic.setSelected(false);
            }
            else {
                ispublic.setSelected(true);
            }
        }

    }
}
