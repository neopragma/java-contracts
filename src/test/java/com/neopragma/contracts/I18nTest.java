package com.neopragma.contracts;

import com.neopragma.resources.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.apache.commons.text.StringEscapeUtils.escapeJava;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

class I18nTest {

    private UpperAndLowerBounds bounds;

    @BeforeEach
    void beforeEach() {
        bounds = new UpperAndLowerBounds();
        Resources.setLocale(Locale.FRANCE);
    }

    @Test
    void it_returns_the_correct_message_for_lower_bound_error() {
        Throwable exception = assertThrows(ContractViolationException.class, ()->
                bounds.isValueInUpper50Percent(30, 9, 50));
        assertThat(escapeJava(exception.getMessage()),
                endsWith(escapeJava("La limite inférieure doit être d'au moins 10")));
    }

    @Test
    void it_returns_the_correct_message_for_upper_bound_error() {
        Throwable exception = assertThrows(ContractViolationException.class, ()->
                bounds.isValueInUpper50Percent(30, 10, 53));
        assertThat(escapeJava(exception.getMessage()),
                endsWith(escapeJava("La limite supérieure doit être inférieure à 51")));
    }

    @Test
    void it_returns_the_correct_message_for_out_of_range_error() {
        Throwable exception = assertThrows(ContractViolationException.class, ()->
                bounds.isValueInUpper50Percent(66, 10, 50));
        assertThat(escapeJava(exception.getMessage()),
                endsWith(escapeJava("La valeur n'est pas dans la plage spécifiée")));
    }

}
