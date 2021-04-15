package me.kecker.lichess4j.model.enums;

import com.google.gson.annotations.SerializedName;

public enum SoundSet {

    @SerializedName("silent")
    SILENT,
    @SerializedName("standard")
    STANDARD,
    @SerializedName("piano")
    PIANO,
    @SerializedName("nes")
    NES,
    @SerializedName("sfx")
    SFX,
    @SerializedName("futuristic")
    FUTURISTIC,
    @SerializedName("robot")
    ROBOT,
    @SerializedName("music")
    MUSIC,
    @SerializedName("speech")
    SPEECH;
}
