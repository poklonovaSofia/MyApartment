package entities;

public class StatiscticOfApartment {
    private int idApartment;
    private String nameOfApartment;
    private int idUser;
    private int numberOfVotes;
    public int getIdApartment() {
        return idApartment;
    }

    public void setIdApartment(int idApartment) {
        this.idApartment = idApartment;
    }

    public String getNameOfApartment() {
        return nameOfApartment;
    }

    public void setNameOfApartment(String nameOfApartment) {
        this.nameOfApartment = nameOfApartment;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }
}
