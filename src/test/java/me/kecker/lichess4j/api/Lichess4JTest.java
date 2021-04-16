package me.kecker.lichess4j.api;

import static org.junit.Assert.*;

import org.junit.Test;

public class Lichess4JTest {

    private static final String BEARER_TOKEN = "123abc456def";

    @Test
    public void constructor_happyDay_createsServices() {
        Lichess4J lichess4j = new Lichess4J(BEARER_TOKEN);
        assertNotNull(lichess4j.account());
        assertNotNull(lichess4j.users());
    }

}
