package entities;

public class Notification {
    int idUser;
    int idApartment;
    String message;
    public Notification(int idUser, int idApartment, String message) {
        this.idUser = idUser;
        this.idApartment = idApartment;
        this.message = message;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    public int getIdApartment() {
        return idApartment;
    }

    public void setIdApartment(int idApartment) {
        this.idApartment = idApartment;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return message;
    }
}
