package utils;

import entities.Apartment;
import entities.Furniture;

/**
 * Listener interface for receiving notifications about vote changes on a post.
 * This interface should be implemented by classes that handle the dynamic
 * updating of vote states (e.g., upvotes or downvotes)
 */
public interface PostListener {
    /**
     * Invoked to change the state of votes for a specific apartment.
     *
     * @param ap the {@link Apartment} object whose vote state is to be changed,
     *           representing the specific apartment post affected by this action.
     * @param b the new state of the vote. If {@code true}, this might represent an
     *          upvote or similar positive action. If {@code false}, it typically
     *          indicates a downvote or withdrawal of a positive vote.
     */
    void changeStateOfVotes(Apartment ap, boolean b);

}
