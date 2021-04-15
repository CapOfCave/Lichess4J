package me.kecker.lichess4j.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum KidModeStatus {
    KID,
    NO_KID;

    public static KidModeStatus of(boolean value) {
        return value ? KID : NO_KID;
    }
}
