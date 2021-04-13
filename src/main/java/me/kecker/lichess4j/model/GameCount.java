package me.kecker.lichess4j.model;

import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class GameCount {

    private int all;
    private int rated;
    private int ai;
    private int draw;
    private int drawH;
    private int loss;
    private int lossH;
    private int win;
    private int winH;
    private int bookmark;
    private int playing;

    @SerializedName("import")
    private int importCount;

    private int me;
}
