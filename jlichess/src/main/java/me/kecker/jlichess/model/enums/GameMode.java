package me.kecker.jlichess.model.enums;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GameMode {

    @SerializedName("chess960")
    CHESS960,

    @SerializedName("atomic")
    ATOMIC,

    @SerializedName("racingKings")
    RACING_KINGS,

    @SerializedName("ultraBullet")
    ULTRA_BULLET,

    @SerializedName("blitz")
    BLITZ,

    @SerializedName("kingOfTheHill")
    KING_OF_THE_HILL,

    @SerializedName("bullet")
    BULLET,

    @SerializedName("correspondence")
    CORRESPONDENCE,

    @SerializedName("horde")
    HORDE,

    @SerializedName("puzzle")
    PUZZLE,

    @SerializedName("classical")
    CLASSICAL,

    @SerializedName("rapid")
    RAPID,

    @SerializedName("storm")
    STORM;

}
