package seedu.address.testutil;

import seedu.address.model.listing.Listing;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;

/**
 * A utility class to help with building Listing objects.
 */
public class ListingBuilder {

    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final int DEFAULT_ASKING_PRICE = 1;

    private Address address;
    private Name name;
    private int askingPrice;

    /**
     * Creates a {@code ListingBuilder} with the default details.
     * @param address address of listing
     * @param name name of owner
     * @param i asking price
     */
    public ListingBuilder(Address address, Name name, int i) {
        this.address = new Address(DEFAULT_ADDRESS);
        this.name = new Name(DEFAULT_NAME);
        askingPrice = DEFAULT_ASKING_PRICE;
    }

    /**
     * Initializes the ListingBuilder with the data of {@code ListingToCopy}.
     */
    public ListingBuilder(Listing listingToCopy) {
        address = listingToCopy.getAddress();
        name = listingToCopy.getName();
        askingPrice = listingToCopy.getAskingPrice();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public ListingBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code askingPrice} of the {@code Listing} that we are building.
     */
    public ListingBuilder withAskingPrice(int askingPrice) {
        this.askingPrice = askingPrice;
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Listing} that we are building.
     */
    public ListingBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    public Listing build() {
        return new Listing(address, name, askingPrice);
    }

}
