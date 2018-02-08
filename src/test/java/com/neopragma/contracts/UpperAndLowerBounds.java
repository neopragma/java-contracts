package com.neopragma.contracts;

import com.neopragma.resources.Resources;

public class UpperAndLowerBounds {

    private static final String LOWER_BOUND_INVALID = "R001";
    private static final String UPPER_BOUND_INVALID = "R002";
    private static final String VALUE_OUT_OF_RANGE = "R003";

    boolean isValueInUpper50Percent(int value, int lowerBound, int upperBound) {
        Contract.require(lowerBound >= 10,
                Resources.messageById(LOWER_BOUND_INVALID));
        Contract.require(upperBound < 51,
                Resources.messageById(UPPER_BOUND_INVALID));
        Contract.require((lowerBound <= value) && (value <= upperBound),
                Resources.messageById(VALUE_OUT_OF_RANGE));
        return value > ((upperBound - lowerBound) / 2);
    }
}
