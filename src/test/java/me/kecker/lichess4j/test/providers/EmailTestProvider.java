package me.kecker.lichess4j.test.providers;

import me.kecker.lichess4j.model.account.Email;

public class EmailTestProvider {

    public static final String EMAIL = "abathur@mail.org";

    public static Email getEmail() {
        return new Email(EMAIL);
    }

    private EmailTestProvider() {
        // this class should not be instantiated
    }
}
