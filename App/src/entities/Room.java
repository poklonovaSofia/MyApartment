package entities;

import java.util.List;

public class Room extends EntityAccommodation{

    private int idType;
    private List<Furniture> furnitureList;

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }


    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }

    public void setFurnitureList(List<Furniture> furnitureList) {
        this.furnitureList = furnitureList;
    }
   @Override
   public String toString() {
       return getTitle();
   }
}
