package entities;


import java.util.ArrayList;
import java.util.List;

public class Apartment {
    private int id;
    private String title;
    private String description;
    private ArrayList<Room> rooms;
    private String createdAt;
    private String updatedAt;
    private Boolean isPublic;
    private int userId;
    public Apartment()
    {
        rooms= new ArrayList<Room>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEditedAt() {
        return updatedAt;
    }

    public void setEditedAt(String editedAt) {
        this.updatedAt = editedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setIsPublic(boolean selected) {
        isPublic = selected;
    }
    public Boolean getIsPublic() {
        return isPublic;
    }

}
