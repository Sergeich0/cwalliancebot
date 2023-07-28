package me.sergeich0.cwalliancebot.controller.bot.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {
    void handle(Update update);
}
