package dev.johnwatts.adventuresinmdc.validators;

import dev.johnwatts.adventuresinmdc.exceptions.ValidationError;
import dev.johnwatts.adventuresinmdc.mdc.MdcUtility;

public class IdValidator {
    public static void validate(Long id) {
        MdcUtility.updateTask( "Validating ID");
        if (id.compareTo(Long.valueOf("0")) == 0) {
            throw new ValidationError("ID was zero");
        }
    }
}
