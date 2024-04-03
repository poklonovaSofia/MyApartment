package components;

import entities.Apartment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.ApartmentModel;

import java.util.Optional;

public class PostControlVote {
    private Apartment apartment;
    private ApartmentModel apartmentModel;
    @FXML
    private Label description;
    @FXML
    private Hyperlink title;
    @FXML
    private CheckBox isVote;
    public void set(Apartment ap) {
        this.apartment=ap;
        description.setText(apartment.getDescription());
        title.setText(apartment.getTitle());
        //isVote.setSelected();

    }

    public void changeState(ActionEvent actionEvent) {
        apartmentModel = new ApartmentModel();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Submit");
        ButtonType buttonTypeYes = new ButtonType("Так", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Ні", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        if(isVote.isSelected()) {
            alert.setContentText("Are you sure you want to make this apartment public?");
        }
        else {
            alert.setContentText("Are you sure you want to make this apartment private?");
        }
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            apartment.setIsPublic(isVote.isSelected());
            apartmentModel.changeStateOfPrivacy(apartment.getId(), apartment.getIsPublic());
        } else {
            if(isVote.isSelected())
            {
                isVote.setSelected(false);
            }
            else {
                isVote.setSelected(true);
            }
        }

    }
}
