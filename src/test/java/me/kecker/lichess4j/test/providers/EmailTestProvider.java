package me.kecker.lichess4j.test.providers;

import me.kecker.lichess4j.model.Email;

public class EmailTestProvider {

    public static final String EMAIL = "abathur@mail.org";

    public static Email getEmail() {
        return new Email(EMAIL);
    }

    private EmailTestProvider() {
        // this class should not be instantiated
    }
}
