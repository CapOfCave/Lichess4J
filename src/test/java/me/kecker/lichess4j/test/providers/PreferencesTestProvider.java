package me.kecker.lichess4j.test.providers;

import java.net.URI;
import me.kecker.lichess4j.model.account.Preferences;
import me.kecker.lichess4j.model.enums.PieceSet;
import me.kecker.lichess4j.model.enums.PieceSet3d;
import me.kecker.lichess4j.model.enums.SoundSet;
import me.kecker.lichess4j.model.enums.Theme;
import me.kecker.lichess4j.model.enums.Theme3d;

public class PreferencesTestProvider {

    private static final boolean DARK = true;
    private static final boolean TRANSP = false;
    private static final URI BG_IMAGE = URI.create("http://example.com");
    private static final boolean IS_3D = false;
    private static final Theme THEME = Theme.BLUE;
    private static final PieceSet PIECE_SET = PieceSet.CBURNETT;
    private static final Theme3d THEME_3D = Theme3d.BLACK_WHITE_ALUMINIUM;
    private static final PieceSet3d PIECE_SET_3D = PieceSet3d.BASIC;
    private static final SoundSet SOUND_SET = SoundSet.SILENT;
    private static final int BLINDFOLD = 0;
    private static final int AUTO_QUEEN = 2;
    private static final int AUTO_THREEFOLD = 2;
    private static final int TAKEBACK = 3;
    private static final int MORE_TIME = 3;
    private static final int CLOCK_TENTHS = 1;
    private static final boolean CLOCK_BAR = true;
    private static final boolean CLOCK_SOUND = true;
    private static final boolean PREMOVE = true;
    private static final int ANIMATION = 2;
    private static final boolean CAPTURED = true;
    private static final boolean FOLLOW = true;
    private static final boolean HIGHLIGHT = true;
    private static final boolean DESTINATION = true;
    private static final int COORD = 2;
    private static final int REPLAY = 2;
    private static final int CHALLENGE = 4;
    private static final int MESSAGE = 3;
    private static final int COORD_COLOR = 2;
    private static final int SUBMIT_MOVE = 4;
    private static final int CONFIRM_RESIGN = 1;
    private static final int INSIGHT_SHARE = 1;
    private static final int KEYBOARD_MOVE = 0;
    private static final int ZEN = 0;
    private static final int MOVE_EVENT = 2;
    private static final int ROOK_CASTLE = 1;

    public static Preferences getPreferences() {
        return new Preferences(DARK, TRANSP, BG_IMAGE, IS_3D, THEME, PIECE_SET, THEME_3D,
                PIECE_SET_3D, SOUND_SET, BLINDFOLD, AUTO_QUEEN, AUTO_THREEFOLD, TAKEBACK, MORE_TIME,
                CLOCK_TENTHS, CLOCK_BAR, CLOCK_SOUND, PREMOVE, ANIMATION, CAPTURED, FOLLOW,
                HIGHLIGHT, DESTINATION, COORD, REPLAY, CHALLENGE, MESSAGE, COORD_COLOR, SUBMIT_MOVE,
                CONFIRM_RESIGN, INSIGHT_SHARE, KEYBOARD_MOVE, ZEN, MOVE_EVENT, ROOK_CASTLE);
    }

    private PreferencesTestProvider() {
        // this class should not be instantiated
    }
}
