package components;

import entities.Apartment;
import entities.Furniture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.ApartmentModel;
import utils.CardListener;
import utils.PostListener;

import java.util.Optional;

public class PostControlVote {
    private Apartment apartment;
    private PostListener listener;

    public void setChangeStateOfVotes(PostListener listener) {
        this.listener = listener;
    }
    private ApartmentModel apartmentModel;
    @FXML
    private Label description;
    @FXML
    private Label count;
    @FXML
    private Hyperlink title;
    @FXML
    private CheckBox isVote;
    public void set(Apartment ap) {
        this.apartment=ap;
        description.setText(apartment.getDescription());
        title.setText(apartment.getTitle());
        count.setText(Integer.toString(apartment.getNumberOfVotes()));

        //isVote.setSelected();

    }

    public void changeState(ActionEvent actionEvent) {
        apartmentModel = new ApartmentModel();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Submit");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        if(isVote.isSelected()) {
            alert.setContentText("Do you want to vote for this apartment?");
        }
        else {
            alert.setContentText("Do you want to cancel your vote for this apartment?");
        }
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            if(isVote.isSelected())
                apartment.setNumberOfVotes(apartment.getNumberOfVotes()+1);
            else
                apartment.setNumberOfVotes(apartment.getNumberOfVotes()-1);
            apartmentModel.changeNumberOfVotes(apartment.getNumberOfVotes(), apartment.getId());
            if (listener != null) {
                listener.changeStateOfVotes(apartment, isVote.isSelected());
            }
            count.setText(Integer.toString(apartment.getNumberOfVotes()));

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

    public void setVoted(boolean b) {
        isVote.setSelected(b);
    }
}
