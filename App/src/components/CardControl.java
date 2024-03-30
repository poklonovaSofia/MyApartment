package components;

import entities.Furniture;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.CardListener;


public class CardControl extends AnchorPane {
    private CardListener listener;
    private Furniture furniture;

    public void setOnCardClickedListener(CardListener listener) {
        this.listener = listener;
    }
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private ImageView image;
    @FXML
    private AnchorPane tool;
    public String getTitle() {
        return title.getText();
    }

    public void setTitle(String titleText) {
        title.setText(titleText);
    }


    public String getDescription() {
        return description.getText();
    }

    public void setDescription(String descriptionText) {

        description.setText(descriptionText);
    }


    public Image getImage() {
        return image.getImage();
    }

    public void setImage(String path) {

        Image img = new Image(getClass().getResourceAsStream(path));
        image.setImage(img);
    }
    public void setUp(float width, float height, String color, String material)
    {
        Tooltip tooltip = new Tooltip("Width: " + width + "\nHeight: " + height + "\nColor: " + color + "\nMaterial: " + material);
        Tooltip.install(tool, tooltip);

    }

    public void doubleClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            if (listener != null) {
                listener.onCardClicked(furniture);
            }
        }

    }

    public void setFurniture(Furniture currentFurniture) {
        furniture = currentFurniture;
        setImage(currentFurniture.getImage());
        setDescription(currentFurniture.getDescription());
        setTitle(currentFurniture.getTitle());
        setUp(currentFurniture.getWidth(), currentFurniture.getHeight(), currentFurniture.getColor(), currentFurniture.getMaterial());
    }
}
