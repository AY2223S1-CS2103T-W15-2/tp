package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LISTING_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFER;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddOfferCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.listing.ListingId;
import seedu.address.model.offer.Offer;
import seedu.address.model.offer.Price;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new AddOfferCommand object
 */
public class AddOfferCommandParser implements Parser<AddOfferCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddOfferCommand
     * and returns an AddOfferCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOfferCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LISTING_ID, PREFIX_NAME, PREFIX_OFFER);

        if (!arePrefixesPresent(argMultimap, PREFIX_LISTING_ID, PREFIX_NAME, PREFIX_OFFER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOfferCommand.MESSAGE_USAGE));
        }

        ListingId listing = ParserUtil.parseListingId(argMultimap.getValue(PREFIX_LISTING_ID).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Price offerPrice = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_OFFER).get());

        Offer offer = new Offer(name, listing, offerPrice);

        return new AddOfferCommand(offer);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
