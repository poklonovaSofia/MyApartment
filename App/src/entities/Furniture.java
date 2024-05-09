package entities;

import java.io.Serializable;

public class Furniture extends EntityAccommodation implements Serializable {

    private String description;
    private String image;
    private float width;
    private float height;
    private String color;
    private String material;
    private int idFurnitureType;

    public Furniture() {

    }
    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String getColor() {
        return color;
    }

    public String getMaterial() {
        return material;
    }

    public int getIdFurnitureType() {
        return idFurnitureType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setIdFurnitureType(int idFurnitureType) {
        this.idFurnitureType = idFurnitureType;
    }
    @Override
    public String toString()
    {
        return getTitle();
    }
}
