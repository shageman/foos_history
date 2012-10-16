package server;


import com.googlecode.objectify.ObjectifyService;
import model.PlayerRating;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@link server.PlayerRatingRepository} class.
 *
 * @author androns
 */
@Test
public class PlayerRatingRepositoryTest extends LocalDatastoreTest {

    private PlayerRatingRepository playerRatingRepository;

    /**
     * @see server.LocalDatastoreTest#setUp()
     */
    @BeforeTest
    @Override
    public void setUp() {
        super.setUp();
        this.playerRatingRepository = new PlayerRatingRepository();
        ObjectifyService.register(PlayerRating.class);
    }

    /**
     *
     */
    @Test
    public void smokeTest() {
        assertNotNull(this.playerRatingRepository);

        // create
        final PlayerRating playerRating = new PlayerRating("Player", new Date(), 99, 13);

        this.playerRatingRepository.create(playerRating);

        // read
        Collection<PlayerRating> playerRatings = this.playerRatingRepository.getAll();

        assertNotNull(playerRatings);
        assertEquals(1, playerRatings.size());
        final PlayerRating storedPlayerRating = playerRatings.iterator().next();

        assertNotNull(storedPlayerRating.getId());
        assertEquals(storedPlayerRating.getPlayerName(), storedPlayerRating.getPlayerName());
        assertEquals(storedPlayerRating.getDate(), storedPlayerRating.getDate());
        assertEquals(storedPlayerRating.getRatingDeviation(), storedPlayerRating.getRatingDeviation());
        assertEquals(storedPlayerRating.getRatingValue(), storedPlayerRating.getRatingValue());

        // delete
        this.playerRatingRepository.deleteById(storedPlayerRating.getId());

        playerRatings = this.playerRatingRepository.getAll();
        assertEquals(0, playerRatings.size());
    }
}
