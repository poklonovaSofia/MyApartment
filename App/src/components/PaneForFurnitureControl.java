package components;

import entities.Furniture;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import models.FurnitureModel;
import models.FurnitureTypeModel;
import utils.PaneListener;

import java.io.IOException;
import java.util.List;

public class PaneForFurnitureControl  extends AnchorPane {
    private PaneListener paneListener;
    public void setPaneListener(PaneListener listener) {
        this.paneListener = listener;
    }
    private String typeOfFurniture;
    private FurnitureTypeModel furnitureTypeModel;
    private FurnitureModel furnitureModel;
    private int ItemOnPage = 9;
    @FXML
    private AnchorPane mainPane;
    private  List<Furniture> furnitureList;

    public void setTypeOfFurniture(String value) {
        typeOfFurniture=value;
    }
    public void fillAnchor()
    {
        furnitureTypeModel=new FurnitureTypeModel();
        furnitureModel=new FurnitureModel();
        furnitureList = furnitureModel.getAllByIdTypeOfFurniture(furnitureTypeModel.getId(typeOfFurniture));
        Pagination pagination = new Pagination((furnitureList.size() / ItemOnPage) + 1, 0);
        //pagination.setPageFactory(pageIndex -> createPage(pageIndex));
        pagination.setPrefHeight(424.0);
        pagination.setPrefWidth(434.0);

        pagination.setPageFactory(new Callback<Integer, Node>() {
            public Node call(Integer pageIndex) {
                GridPane gridPane = new GridPane();
                gridPane.setHgap(10);
                gridPane.setVgap(10);

                int page = pageIndex * ItemOnPage;
                for (int i = page; i < page + ItemOnPage && i < furnitureList.size(); i++) {
                    int row = (i - page) / 3;
                    int col = (i - page) % 3;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/CardControl.fxml"));
                    try {
                        Node content = loader.load();
                        CardControl cardControl = loader.getController();

                        Furniture currentFurniture = furnitureList.get(i);
                        cardControl.setFurniture(currentFurniture);
                        cardControl.setOnCardClickedListener(furniture -> {
                            if (paneListener != null) {
                                paneListener.onFurnitureSelected(furniture);
                            }
                        });

                        gridPane.add(content, col, row);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                return gridPane;
            }
        });
        mainPane.getChildren().add(pagination);

    }

}
