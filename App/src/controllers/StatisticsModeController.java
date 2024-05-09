package controllers;

import entities.StatiscticOfApartment;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import models.ApartmentModel;
import models.UserModel;
import utils.ModeControllerInterface;

import java.io.IOException;
import java.util.List;

public class StatisticsModeController extends AbstractController implements ModeControllerInterface {
    private ApartmentModel apartmentModel;
    private UserModel userModel;
    @FXML
    private ListView<HBox> listLeaders;

    @FXML
    private AnchorPane anchorPane;
    /**
     * Fills the user interface with statistical data regarding public apartments.
     * This method sets up the visual representation of the statistics by creating progress bars for each apartment
     * based on the number of votes received. It retrieves statistical information about public apartments
     * from the {@link ApartmentModel} and calculates the progress bar values relative to the minimum and maximum
     * number of votes among the apartments. Each progress bar is accompanied by a label displaying the name of the apartment
     * and the ID of the user who owns it.
     * The statistical data is displayed within a {@link ListView} component and added to the {@link AnchorPane}.
     * Visual styling is applied to enhance the appearance of the progress bars and labels.
     *
     * @see ApartmentModel
     * @see StatiscticOfApartment
     */

    public void fill() {
        mainPane.getStylesheets().add("/styles/styles.css");
        apartmentModel=new ApartmentModel();
        userModel= new UserModel();
        List<StatiscticOfApartment> statiscticOfApartmentList = apartmentModel.getAllPublicAp();
        listLeaders = new ListView<>();
        int minVal = 0;
        int maxVal = statiscticOfApartmentList.getFirst().getNumberOfVotes();
        ObservableList<HBox> items = FXCollections.observableArrayList();
        for (int i = 0; i < statiscticOfApartmentList.size(); i++) {
            StatiscticOfApartment temp = statiscticOfApartmentList.get(i);
            double valForAp = (double)(temp.getNumberOfVotes() - minVal) / (maxVal - minVal);
            //Rectangle bar = new Rectangle(temp.getNumberOfVotes() * 10, 30);
            ProgressBar progressBar = new ProgressBar();
            progressBar.setPrefWidth(500.0);

            progressBar.setStyle("-fx-background-color: #eff7f6;");


            progressBar.setStyle("-fx-accent: #f2b5d4;");
            progressBar.setProgress(valForAp);
            User user = userModel.getUser(temp.getInheritId());
            Label label = new Label(temp.getTitle() + " " + user.getUsername());
            label.setStyle("-fx-font-family: 'Segoe Print'; -fx-padding: 0 0 0 5;");


            HBox box = new HBox(progressBar, label);
            items.add(box);
        }
        listLeaders.setItems(items);
        listLeaders.setLayoutX(47.0);
        listLeaders.setLayoutY(74.0);
        listLeaders.setPrefHeight(379.0);
        listLeaders.setPrefWidth(709.0);
        anchorPane.getChildren().add(listLeaders);

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
