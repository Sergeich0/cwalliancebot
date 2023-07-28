package me.sergeich0.cwalliancebot.controller.bot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BotCommands {
    ADMIN_PANEL("/admin_panel");

    private final String command;

    @Override
    public String toString() {
        return this.command;
    }
}
