package server;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import model.PlayerRating;

import java.util.Collection;


/**
 */
public class PlayerRatingRepository {
    /**
     * @return Collection of PlayerRating
     */
    public Collection<PlayerRating> getAll() {
        final Objectify service = getService();

        return(service.query(PlayerRating.class).list());
    }

    /**
     * @param playerRating
     */
    public void create(final PlayerRating playerRating) {
        final Objectify service = getService();

        service.put(playerRating);
    }

    /**
     * @param id
     */
    public void deleteById(final Long id) {
        final Objectify service = getService();

        service.delete(PlayerRating.class, id.longValue());
    }

    private Objectify getService() {
        return (ObjectifyService.begin());
    }
}
