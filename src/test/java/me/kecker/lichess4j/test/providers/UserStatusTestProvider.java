package me.kecker.lichess4j.test.providers;

import me.kecker.lichess4j.model.enums.Title;
import me.kecker.lichess4j.model.users.UserStatus;

public class UserStatusTestProvider {

    private static final String ID2 = "aliquantus";
    private static final String NAME2 = "aliquantus";

    private static final String ID1 = "chess-network";
    private static final String NAME1 = "Chess-Network";
    private static final Title TITLE1 = Title.NM;
    private static final Boolean ONLINE1 = Boolean.TRUE;
    private static final Boolean PLAYING1 = Boolean.TRUE;
    private static final Boolean STREAMING1 = Boolean.TRUE;
    private static final Boolean PATRON1 = Boolean.TRUE;

    public static UserStatus getUserStatus() {
        return new UserStatus(ID2, NAME2);
    }

    public static UserStatus getFullUserStatus() {
        return new UserStatus(ID1, NAME1, TITLE1, ONLINE1, PLAYING1, STREAMING1, PATRON1);
    }

    private UserStatusTestProvider() {
        // this class should not be instantiated
    }
}
