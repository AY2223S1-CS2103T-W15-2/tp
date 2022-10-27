package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.listing.ListingId;
import seedu.address.model.person.Name;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewListingClientsCommand extends Command {

    public static final String COMMAND_WORD = "listing clients";

    public static final String MESSAGE_SUCCESS = "Listed all listing clients";

    private final ListingId id;
    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public ViewListingClientsCommand(ListingId id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        final StringBuilder builder = new StringBuilder();
        for (Name client : model.getListing(id).getInterestedClients()) {
            builder.append(client).append("\n");
        }
        return new CommandResult(MESSAGE_SUCCESS + "\n" + builder);
    }
}


