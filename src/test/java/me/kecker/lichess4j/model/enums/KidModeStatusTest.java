package me.kecker.lichess4j.model.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class KidModeStatusTest {

    @Test
    public void of_happyDay_returnsCorrectKidModeStatus() {
        assertEquals(KidModeStatus.KID, KidModeStatus.of(true));
        assertEquals(KidModeStatus.NO_KID, KidModeStatus.of(false));
    }
}
