package model;

import javax.persistence.Id;
import java.util.Date;

/**
 */
public class PlayerRating {
    @Id
    private Long id;
    private String playerName;
    private Date date;
    private Integer ratingValue;
    private Integer ratingDeviation;

    /**
     */
    public PlayerRating() {
        super();
    }

    /**
     * @param playerName
     * @param date
     * @param ratingValue
     * @param ratingDeviation
     */
    public PlayerRating(String playerName, Date date, Integer ratingValue, Integer ratingDeviation) {
        this.playerName = playerName;
        this.date = date;
        this.ratingDeviation = ratingDeviation;
        this.ratingValue = ratingValue;
    }

    /**
     * @return id of the message
     */
    public Long getId() {
        return this.id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Date getDate() {
        return date;
    }

    public Integer getRatingValue() {
        return ratingValue;
    }

    public Integer getRatingDeviation() {
        return ratingDeviation;
    }
}
