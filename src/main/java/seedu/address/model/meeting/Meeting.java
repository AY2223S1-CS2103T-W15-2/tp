package seedu.address.model.meeting;

import java.time.LocalDateTime;

import seedu.address.model.listing.Listing;
import seedu.address.model.client.Client;

/**
 * Meeting class is a meeting a client has at a listing on a specific dateTime.
 */
public class Meeting {

    /**
     * Date and Time of meeting.
     */
    private final LocalDateTime dateTime;
    /**
     * Client involved in the meeting.
     */
    private final Client client;
    /**
     * Listing involved in the meeting.
     */
    private final Listing listing;

    /**
     * Constructor for meeting.
     * @param client Client
     * @param listing Listing
     * @param dateTime dateTime
     */
    public Meeting(Client client, Listing listing, LocalDateTime dateTime) {
        this.client = client;
        this.listing = listing;
        this.dateTime = dateTime;
    }

    /**
     * Getter for client.
     * @return Client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Getter for listing.
     * @return Listing
     */
    public Listing getListing() {
        return listing;
    }

    /**
     * Getter for date time.
     * @return LocalDatetime
     */
    public LocalDateTime getdateTime() {
        return dateTime;
    }

    /**
     * String representation of meeting.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s is meeting at %s on %s", client.getName(), listing.getAddress(), dateTime);
    }
}
