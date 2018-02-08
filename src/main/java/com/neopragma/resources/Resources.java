package com.neopragma.resources;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Looks up a message from a ResourceBundle and optionally inserts substitution values
 * into the text using {@link java.text.MessageFormat#format(String, Object...)}
 */
public class Resources {

    private static final String NOT_FOUND = "Message id {0} not found";
    private static final String BUNDLE_BASE_NAME = "messages";
    private static ResourceBundle messages = ResourceBundle.getBundle(BUNDLE_BASE_NAME, Locale.getDefault());

    private Resources() {}

    /**
     * Set a locale other than the system default for messages.
     * The base name for the resource bundle is "messages".
     * @param locale The Locale to set
     */
    public static void setLocale(Locale locale) {
        messages = ResourceBundle.getBundle(BUNDLE_BASE_NAME, locale);
    }

    /**
     * Looks up a message string from a ResourceBundle.
     * @param id key for looking up the message.
     * @return String message text from the ResourceBundle.
     */
    public static String messageById(String id) {
        return messageById(id, (String) null);
    }

    /**
     * Looks up a message string from a ResourceBundle and applies any substitution
     * values that were passed.
     * @param id key for looking up the message.
     * @param substitutionValues to insert into the message text, if there are
     * any placeholders.
     * @return String message text will substitution values applied.
     */
    public static String messageById(String id, String...substitutionValues) {
        String message;
        try {
            message = messages.getString(id);
        } catch (MissingResourceException mre) {
            message = NOT_FOUND;
        } catch (Exception unexpectedException) {
            throw new RuntimeException("Unexpected exception accessing resource bundle 'messages'",
                    unexpectedException);
        }
        return MessageFormat.format(message, (Object) substitutionValues);
    }
}
