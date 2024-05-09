package entities;

public class StatiscticOfApartment extends Apartment{
    @Override
    public String toString() {
        return Integer.toString(numberOfVotes);
    }

}
