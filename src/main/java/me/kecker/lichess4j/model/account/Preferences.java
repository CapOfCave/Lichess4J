package me.kecker.lichess4j.model.account;

import com.google.gson.annotations.SerializedName;
import java.net.URI;
import lombok.Value;
import me.kecker.lichess4j.model.enums.PieceSet;
import me.kecker.lichess4j.model.enums.PieceSet3d;
import me.kecker.lichess4j.model.enums.SoundSet;
import me.kecker.lichess4j.model.enums.Theme;
import me.kecker.lichess4j.model.enums.Theme3d;

@Value
public class Preferences {

    private boolean dark;
    private boolean transp;
    private URI bgImage;
    private boolean is3d;
    private Theme theme;
    private PieceSet pieceSet;
    private Theme3d theme3d;
    private PieceSet3d pieceSet3d;
    private SoundSet soundSet;
    private int blindfold;
    private int autoQueen;
    private int autoThreefold;
    private int takeback;
    @SerializedName("moretime")
    private int moreTime;
    private int clockTenths;
    private boolean clockBar;
    private boolean clockSound;
    private boolean premove;
    private int animation;
    private boolean captured;
    private boolean follow;
    private boolean highlight;
    private boolean destination;
    private int coord;
    private int replay;
    private int challenge;
    private int message;
    private int coordColor;
    private int submitMove;
    private int confirmResign;
    private int insightShare;
    private int keyboardMove;
    private int zen;
    private int moveEvent;
    private int rookCastle;

}
