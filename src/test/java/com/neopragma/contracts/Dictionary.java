package com.neopragma.contracts;

import com.neopragma.resources.Resources;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    private int capacity;
    private Map<String, Object> contents;
    private static final String CAPACITY_MUST_BE_POSITIVE = "D001";
    private static final String COUNT_MUST_BE_ZERO_ON_CREATION = "D002";
    private static final String DICTIONARY_IS_FULL = "D003";
    private static final String EMPTY_KEY_NOT_ALLOWED = "D004";

    Dictionary(int capacity) {
        Contract.require(capacity > 0,
                Resources.messageById(CAPACITY_MUST_BE_POSITIVE));
        this.capacity = capacity;
        contents = new HashMap<>(capacity);
        Contract.ensure(contents.size() == 0,
                Resources.messageById(COUNT_MUST_BE_ZERO_ON_CREATION));
    }

    public int capacity() {
        return capacity;
    }

    public int count() {
        return contents.size();
    }

    public void put(String key, Object value) {
        Contract.require(StringUtils.isNotEmpty(key),
                Resources.messageById(EMPTY_KEY_NOT_ALLOWED));
        Contract.require(contents.size() < capacity,
                Resources.messageById(DICTIONARY_IS_FULL));
        contents.put(key, value);
    }

    public Object get(String key) {
        return contents.get(key);
    }
}
