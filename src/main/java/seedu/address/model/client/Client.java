package seedu.address.model.client;
import seedu.address.model.listing.Listing;
import seedu.address.model.offer.Offer;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

import java.util.ArrayList;

public class Client {


    /**
     * Name of the client.
     */
    private final Person person;

    /**
     * List of offers that the client has made.
     */
    private final ArrayList<Offer> offer;
    /**
     * List of listings the client has made.
     */
    private final ArrayList<Listing> listing;
    /**
     * List of meetings the client has made.
     */
    private ArrayList<Meeting> meeting;



    /**
     * Constructor for offer object.
     *
     * @param name Person
     * @param offerList ArrayList<Offer>
     * @param meetingList ArrayList<Meeting>
     * @param listingList ArrayList<Listing>
     */

    public Client(Person name, ArrayList<Offer> offerList, ArrayList<Meeting> meetingList,
                  ArrayList<Listing> listingList) {

        this.person = name;
        this.offer = offerList;
        this.listing = listingList;
        this.meeting = meetingList;

    }


    /**
     * Getter for clients name.
     * @return Person
     */
    public Person getName() {
        return person;
    }

    /**
     * Getter for listing list.
     * @return ArrayList<Listing>
     */
    public ArrayList<Listing> getListingList() {
        return listing;
    }

    /**
     * Getter for offer list.
     * @return ArrayList<Offer>
     */
    public ArrayList<Offer> getOfferList() {
        return offer;
    }

    /**
     * Getter for meeting list.
     * @return ArrayList<Meeting>
     */
    public ArrayList<Meeting> getMeetingList() {
        return meeting;
    }
}
