package me.kecker.lichess4j.test.providers;

import me.kecker.lichess4j.model.GameCount;

public final class GameCountTestProvider {

    private static final int ALL = 9265;
    private static final int RATED = 7157;
    private static final int AI = 531;
    private static final int DRAW = 340;
    private static final int DRAW_H = 331;
    private static final int LOSS = 4480;
    private static final int LOSS_H = 4207;
    private static final int WIN = 4440;
    private static final int WIN_H = 4378;
    private static final int BOOKMARK = 71;
    private static final int PLAYING = 6;
    private static final int IMPORT_COUNT = 66;
    private static final int ME = 0;

    public static GameCount getCameCount() {
        return new GameCount(ALL, RATED, AI, DRAW, DRAW_H, LOSS, LOSS_H, WIN, WIN_H, BOOKMARK,
                PLAYING, IMPORT_COUNT, ME);
    }
    
    private GameCountTestProvider() {
        // this class should not be instantiated
    }
}
