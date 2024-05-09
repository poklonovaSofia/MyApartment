package entities;


import java.util.ArrayList;
import java.util.List;

public class Apartment extends EntityAccommodation {
    private String description;
    private ArrayList<Room> rooms;
    private String createdAt;
    private String updatedAt;
    private Boolean isPublic;
    protected int numberOfVotes;
    public Apartment()
    {
        rooms= new ArrayList<Room>();
    }


    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
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



    public void setIsPublic(boolean selected) {
        isPublic = selected;
    }
    public Boolean getIsPublic() {
        return isPublic;
    }

}
