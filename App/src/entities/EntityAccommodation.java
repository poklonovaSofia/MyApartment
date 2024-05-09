package entities;



public class EntityAccommodation {
    private int id;
    private String title;
    private int inheritId;
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
    public int getInheritId() {
        return inheritId;
    }

    public void setInheritId(int inheritId) {
        this.inheritId = inheritId;
    }

}
