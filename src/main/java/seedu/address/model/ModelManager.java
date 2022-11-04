package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.ListingId;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.offer.Offer;
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Listing> filteredListings;
    private final FilteredList<Offer> filteredOffers;
    private final FilteredList<Meeting> filteredMeetings;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredClients = new FilteredList<>(this.addressBook.getClientList());
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredListings = new FilteredList<>(this.addressBook.getListingList());
        filteredOffers = new FilteredList<>(this.addressBook.getOfferList());
        filteredMeetings = new FilteredList<>(this.addressBook.getMeetingList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public Person getPerson(Name name) {
        return addressBook.getPerson(name);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }


    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return addressBook.hasClient(client);
    }

    @Override
    public void deleteClient(Client target) {
        addressBook.removeClient(target);
    }

    @Override
    public void deleteListingsOwnedBy(Client target) {
        addressBook.removeAllListingOwnedBy(target);
    }

    @Override
    public void deleteOffersMadeBy(Client target) {
        addressBook.removeAllOffersMadeBy(target);
    }

    @Override
    public void deleteMeetingsWith(Client target) {
        addressBook.removeAllMeetingsWith(target);
    }

    @Override
    public void addClient(Client client) {
        addressBook.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public Client getClient(Name name) {
        return addressBook.getClient(name);
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        addressBook.setClient(target, editedClient);
    }


    @Override
    public boolean hasListing(Listing listing) {
        requireNonNull(listing);
        return addressBook.hasListing(listing);
    }

    @Override
    public void deleteListing(Listing target) {
        addressBook.removeListing(target);
    }

    @Override
    public void addListing(Listing listing) {
        addressBook.addListing(listing);
        updateFilteredListingList(PREDICATE_SHOW_ALL_LISTINGS);
    }

    /**
     * Gets the listing with the given id {@code id}.
     * @param id id of the listing
     * @return listing with given id
     */
    public Listing getListing(ListingId id) {
        return addressBook.getListing(id);
    }

    @Override
    public void setListing(Listing target, Listing editedListing) {
        requireAllNonNull(target, editedListing);
        addressBook.setListing(target, editedListing);
    }

    @Override
    public void deleteOffersFor(Listing target) {
        addressBook.removeAllOffersFor(target);
    }

    @Override
    public void deleteMeetingsAbout(Listing target) {
        addressBook.removeAllMeetingsAbout(target);
    }

    @Override
    public boolean hasOffer(Offer offer) {
        requireNonNull(offer);
        return addressBook.hasOffer(offer);
    }

    @Override
    public void deleteOffer(Offer target) {
        addressBook.removeOffer(target);
    }

    @Override
    public void addOffer(Offer offer) {
        addressBook.addOffer(offer);
        updateFilteredOfferList(PREDICATE_SHOW_ALL_OFFERS);
    }

    @Override
    public Offer getOffer(Name name, Address address) {
        return addressBook.getOffer(name, address);
    }

    @Override
    public void setOffer(Offer target, Offer editedOffer) {
        requireAllNonNull(target, editedOffer);

        addressBook.setOffer(target, editedOffer);
    }

    @Override
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return addressBook.hasMeeting(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        addressBook.removeMeeting(meeting);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        addressBook.addMeeting(meeting);
        updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
    }

    @Override
    public Meeting getMeeting(Name name, Address address) {
        return addressBook.getMeeting(name, address);
    }

    @Override
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        addressBook.setMeeting(target, editedMeeting);
    }

    //=========== Filtered List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }


    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    @Override
    public ObservableList<Listing> getFilteredListingList() {
        return filteredListings;
    }

    @Override
    public void updateFilteredListingList(Predicate<Listing> predicate) {
        requireNonNull(predicate);
        filteredListings.setPredicate(predicate);
    }

    @Override
    public ObservableList<Offer> getFilteredOfferList() {
        return filteredOffers;
    }

    @Override
    public void updateFilteredOfferList(Predicate<Offer> predicate) {
        requireNonNull(predicate);
        filteredOffers.setPredicate(predicate);
    }

    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return filteredMeetings;
    }

    @Override
    public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
        requireNonNull(predicate);
        filteredMeetings.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredClients.equals(other.filteredClients)
                && filteredListings.equals(other.filteredListings)
                && filteredOffers.equals(other.filteredOffers)
                && filteredMeetings.equals(other.filteredMeetings);
    }
}
