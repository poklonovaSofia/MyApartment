package controllers;

import entities.StatiscticOfApartment;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import models.ApartmentModel;
import utils.ModeControllerInterface;

import java.util.List;

public class StatisticsModeController implements ModeControllerInterface {
    private ApartmentModel apartmentModel;
    @FXML
    private ListView<HBox> listLeaders;
    @FXML
    private BorderPane mainPane;
    @FXML
    private AnchorPane anchorPane;
    private User user;
    public void fill() {
        mainPane.getStylesheets().add("/styles/styles.css");
        apartmentModel=new ApartmentModel();
        List<StatiscticOfApartment> statiscticOfApartmentList = apartmentModel.getAllPublicAp();
        listLeaders = new ListView<>();
        int minVal = statiscticOfApartmentList.getLast().getNumberOfVotes()-1;
        int maxVal = statiscticOfApartmentList.getFirst().getNumberOfVotes()+1;
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
            Label label = new Label(temp.getNameOfApartment() + " " + temp.getIdUser());
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

    public void setUser(User user) {
        this.user = user;
    }
}
