package me.kecker.lichess4j.model;

import lombok.Value;

/**
 * Represents an email response from the Lichess API.
 * <p>
 * This class should not be passed to the public API due to lack of necessity.
 * Instead, the wrapped {@link #email} String should be returned.
 *
 */
@Value
public class Email {
    private String email;
}
