package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteOfferCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteOfferCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteOfferCommand
     * and returns a DeleteOfferCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteOfferCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteOfferCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOfferCommand.MESSAGE_USAGE), pe);
        }
    }
}
