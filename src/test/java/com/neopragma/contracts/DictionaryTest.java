package com.neopragma.contracts;


import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DictionaryTest {

    @Test void it_creates_dictionary_with_specified_capacity() {
        assertThat(new Dictionary(5).capacity(), is(equalTo(5)));
    }

    @Test void it_rejects_a_negative_capacity() {
        assertThrows(ContractViolationException.class, ()->
                new Dictionary(-1));
    }

    @Test void on_creation_the_count_is_zero() {
        assertThat(new Dictionary(5).count(), is(equalTo(0)));
    }

    @Test void an_entry_can_be_added_and_looked_up_by_its_key() {
        Dictionary dict = new Dictionary(1);
        dict.put("key1", "value1");
        assertThat(dict.get("key1"), is(equalTo("value1")));
    }

    @Test void after_adding_an_entry_the_count_is_increased_by_one() {
        Dictionary dict = new Dictionary(1);
        int expectedCount = dict.count() + 1;
        dict.put("key1", "value1");
        assertThat(dict.count(), is(equalTo(expectedCount)));
    }

    @Test void it_will_not_accept_an_entry_with_a_null_key() {
        Dictionary dict = new Dictionary(1);
        assertThrows(ContractViolationException.class, ()->
                dict.put(null, "value1"));
    }

    @Test void it_will_not_accept_an_entry_with_an_empty_key() {
        Dictionary dict = new Dictionary(1);
        assertThrows(ContractViolationException.class, ()->
                dict.put("", "value1"));
    }

    @Test void it_will_not_accept_an_entry_when_it_is_full() {
        Dictionary dict = new Dictionary(1);
        dict.put("key1", "value1");
        assertThrows(ContractViolationException.class, ()->
                dict.put("key2", "value2"));
    }
}
