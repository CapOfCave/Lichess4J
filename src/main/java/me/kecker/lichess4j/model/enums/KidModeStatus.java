package me.kecker.lichess4j.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum KidModeStatus {
    KID(true),
    NO_KID(false);

    private boolean value;

    public static KidModeStatus of(boolean value) {
        return value ? KID : NO_KID;
    }
    
    public boolean getValue() {
        return this.value;
    }
}
