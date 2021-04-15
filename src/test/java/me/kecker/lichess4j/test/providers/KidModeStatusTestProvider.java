package me.kecker.lichess4j.test.providers;

import me.kecker.lichess4j.model.account.KidModeStatusWrapper;
import me.kecker.lichess4j.model.enums.KidModeStatus;

public class KidModeStatusTestProvider {

    private static final boolean VALUE = false;
    
    public static KidModeStatusWrapper getKidModeStatusWrapper() {
        return new KidModeStatusWrapper(VALUE);
    }

    public static Object getKidModeStatus() {
        return KidModeStatus.of(VALUE);
    }

}
