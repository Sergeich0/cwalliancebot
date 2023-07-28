package me.sergeich0.cwalliancebot.controller.bot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PoiType {
    HEADQUARTER("headquarter"),
    LOCATION("location");

    private final String type;

    public static PoiType getByType(String type) {
        for (PoiType p : values()) {
            if (p.type.equals(type)) {
                return p;
            }
        }
        return null;
    }
}
