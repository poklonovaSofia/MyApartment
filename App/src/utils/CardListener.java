package utils;

import entities.Furniture;
/**
 * Listener interface for receiving "card click" events from UI components.
 * This interface should be implemented by classes that handle the interaction
 * with cards representing furniture items in a user interface.
 */
public interface CardListener {
    /**
     * Invoked when a furniture card is clicked.
     * @param furniture the {@link Furniture} object that was clicked, providing
     *                  details about the furniture item represented by the card.
     */
    void onCardClicked(Furniture furniture);
}
