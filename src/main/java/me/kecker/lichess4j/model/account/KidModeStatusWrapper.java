package me.kecker.lichess4j.model.account;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

/**
 * Represents a kid moderesponse from the Lichess API.
 * <p>
 * This class should not be passed to the public API due to lack of necessity.
 * Instead, the wrapped {@link #kidModeStatus} boolean should be returned.
 *
 */
@Value
public class KidModeStatusWrapper {
    @Getter(AccessLevel.NONE)
    @SerializedName("kid")
    private boolean kidModeStatus;
    
    public boolean getKidModeStatus() {
        return kidModeStatus;
    }
}
