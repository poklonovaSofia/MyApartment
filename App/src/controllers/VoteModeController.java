package controllers;

import components.PostControl;
import components.PostControlVote;
import entities.Apartment;
import entities.User;

import entities.Vote;
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
import utils.ResultsOfVote;

import java.io.IOException;
import java.util.List;

public class VoteModeController extends AbstractController implements ModeControllerInterface {

    private ApartmentModel apartmentModel;
    private UsersVoteModel usersVoteModel;

    @FXML
    private ListView<AnchorPane> listViewApartments;
    /**
     * Populates the user interface with apartments available for voting.
     * This method retrieves a list of apartments from the database using the {@link ApartmentModel}.
     * It also retrieves the user's votes from the database using the {@link UsersVoteModel}.
     * For each apartment retrieved, it creates a corresponding UI element using the "PostControlVote" view template.
     * The method sets up event handlers for voting actions on each apartment, allowing the user to vote or remove their vote.
     * The voting status of each apartment is determined based on whether the user has already voted for it.
     * Upon voting or removing a vote, the method updates the database accordingly and notifies the relevant apartment owner
     * using the {@link ResultsOfVote} observer pattern implementation.
     * The populated UI elements are added to a {@link ListView} component for display.
     *
     * @see ApartmentModel
     * @see UsersVoteModel
     * @see ResultsOfVote
     * @see PostControlVote
     */
    public void fill() {
        apartmentModel=new ApartmentModel();
        usersVoteModel = new UsersVoteModel();
        user.setVotesList(usersVoteModel.getAllApartmentById(user.getId()));
        List<Apartment> apartmentList =apartmentModel.getAllApartWithoutUser(user.getId());
        for(Apartment ap: apartmentList)
        {
            ResultsOfVote resultsOfVote =new ResultsOfVote();
            resultsOfVote.registerObserver(ap.getInheritId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PostControlVote.fxml"));
            try {

                AnchorPane content = loader.load();
                PostControlVote controller = loader.getController();
                controller.set(ap);
                if (isVoted(ap.getId(), user.getVotesList())) {
                    controller.setVoted(true);
                }
                controller.setChangeStateOfVotes((apa, vt) -> {
                    if(vt) {
                        if (!isVoted(apa.getId(), user.getVotesList())) {
                            Vote usersVote = usersVoteModel.addUsersVote(apa.getId(), user.getId());
                            user.getVotesList().add(usersVote);
                            resultsOfVote.notifyObservers(apa.getInheritId(),"Someone vote for your apartment:" + apa.getTitle(), apa.getId());
                        }

                    }
                    else
                    {
                        if (isVoted(apa.getId(), user.getVotesList())) {

                            int idVote = usersVoteModel.findVoteId(user.getId(), apa.getId());
                            usersVoteModel.deleteUsersVote(apa.getId(), user.getId());
                            Vote usersVoteToRemove = null;
                            for (Vote vote : user.getVotesList()) {
                                if (vote.getId() == idVote) {
                                    usersVoteToRemove = vote;
                                    break;
                                }
                            }
                            if (usersVoteToRemove != null) {
                                user.getVotesList().remove(usersVoteToRemove);
                                resultsOfVote.notifyObservers(apa.getInheritId(),"Someone remove vote for your apartment:" + apa.getTitle(), apa.getId());
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
    private boolean isVoted(int apartmentId, List<Vote> usersVotes) {
        for (Vote vote : usersVotes) {
            if (vote.getIdApartment() == apartmentId) {
                return true;
            }
        }
        return false;
    }


    public void returnBack(ActionEvent actionEvent) {
        loadScene("/views/MenuMode.fxml", user);
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

}
