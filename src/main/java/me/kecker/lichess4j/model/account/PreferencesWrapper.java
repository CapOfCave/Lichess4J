package me.kecker.lichess4j.model.account;

import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class PreferencesWrapper {
    @SerializedName("prefs")
    private Preferences preferences;
}
