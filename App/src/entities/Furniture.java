package entities;

import java.io.Serializable;

public class Furniture implements Serializable {
    private int id;
    private String title;
    private String description;
    private String image;
    private float width;
    private float height;
    private String color;
    private String material;
    private int idFurnitureType;

    public Furniture() {

    }

    public int getId(){return this.id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
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

    // Setters
    public void setTitle(String title) {
        this.title = title;
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
        return title;
    }
}
