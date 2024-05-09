package utils;
import entities.Furniture;
public interface PaneListener {
    /**
     * Invoked when a furniture item is selected.
     * @param furniture the {@link Furniture} object that was selected, providing
     *                  details about the selected furniture item. This can help the listener
     *                  implement appropriate actions based on the specific furniture selected.
     */
    void onFurnitureSelected(Furniture furniture);
}
