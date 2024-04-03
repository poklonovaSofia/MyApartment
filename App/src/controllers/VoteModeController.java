package controllers;

import components.PostControl;
import components.PostControlVote;
import entities.Apartment;
import entities.User;
import entities.UsersVote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.ApartmentModel;
import models.UsersVoteModel;
import utils.ModeControllerInterface;

import java.io.IOException;
import java.util.List;

public class VoteModeController implements ModeControllerInterface {
    private User user;
    private ApartmentModel apartmentModel;
    private UsersVoteModel usersVoteModel;
    @FXML
    private BorderPane mainPane;
    @FXML
    private ListView<AnchorPane> listViewApartments;
    public void fill() {
        apartmentModel=new ApartmentModel();
        usersVoteModel = new UsersVoteModel();
        List<UsersVote> usersVotes = usersVoteModel.getAllApartmentById(user.getId());
        List<Apartment> apartmentList =apartmentModel.getAllApartWithoutUser(user.getId());
        for(Apartment ap: apartmentList)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PostControlVote.fxml"));
            try {

                AnchorPane content = loader.load();
                PostControlVote controller = loader.getController();
                controller.set(ap);
                if (isVoted(ap.getId(), usersVotes)) {
                    controller.setVoted(true);
                }
                controller.setChangeStateOfVotes((apa, vt) -> {
                    if(vt) {
                        if (!isVoted(apa.getId(), usersVotes)) {
                            UsersVote usersVote = usersVoteModel.addUsersVote(apa.getId(), user.getId());
                            usersVotes.add(usersVote);
                        }

                    }
                    else
                    {
                        if (isVoted(apa.getId(), usersVotes)) {
                            int idVote = usersVoteModel.deleteUsersVote(apa.getId(), user.getId());
                            UsersVote usersVoteToRemove = null;
                            for (UsersVote vote : usersVotes) {
                                if (vote.getId() == idVote) {
                                    usersVoteToRemove = vote;
                                    break;
                                }
                            }
                            if (usersVoteToRemove != null) {
                                usersVotes.remove(usersVoteToRemove);
                            }
                        }
                    }

                });
                listViewApartments.getItems().add(content);

            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private boolean isVoted(int apartmentId, List<UsersVote> usersVotes) {
        for (UsersVote vote : usersVotes) {
            if (vote.getIdApartment() == apartmentId) {
                return true;
            }
        }
        return false;
    }
    public void setUser(User user) {
        this.user=user;
    }

    public void returnBack(ActionEvent actionEvent) {
        loadScene("/views/MenuMode.fxml", user);
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

}
