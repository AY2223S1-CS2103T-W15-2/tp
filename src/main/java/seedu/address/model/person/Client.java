package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.listing.Listing;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.offer.Offer;
import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable
 * except meetinglist, listinglist and offerlist.
 */

public class Client extends Person implements Comparable<Client> {

    private ArrayList<Meeting> meetinglist;
    private ArrayList<Listing> listinglist;
    private ArrayList<Offer> offerlist;


    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Client(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.meetinglist = new ArrayList<Meeting>();
        this.listinglist = new ArrayList<Listing>();
        this.offerlist = new ArrayList<Offer>();
    }


    public ArrayList<Meeting> getMeetingList() {
        return this.meetinglist;
    }

    public ArrayList<Offer> getOfferList() {
        return this.offerlist;
    }

    public ArrayList<Listing> getListinglist() {
        return this.listinglist;
    }

    /**
     * Returns true if both client have the same name.
     * This defines a weaker notion of equality between two client.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    @Override
    public int compareTo(Client o) {
        return this.getName().fullName.compareTo(o.getName().fullName);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; No. of Meetings: ")
                .append(getMeetingList().size())
                .append("; No. of Offers: ")
                .append(getOfferList().size())
                .append("; No. of Listings: ")
                .append(getListinglist().size());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}

