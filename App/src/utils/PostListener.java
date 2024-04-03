package utils;

import entities.Apartment;
import entities.Furniture;

public interface PostListener {
    void changeStateOfVotes(Apartment ap, boolean b);
}
