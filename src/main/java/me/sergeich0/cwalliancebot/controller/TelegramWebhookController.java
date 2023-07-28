package me.sergeich0.cwalliancebot.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.WebhookBot;

@RestController
@AllArgsConstructor
public class TelegramWebhookController {

    WebhookBot bot;

    @PreAuthorize("permitAll()")
    @PostMapping("/callback")
    BotApiMethod<?> getUpdates(@RequestBody Update update) {
        return bot.onWebhookUpdateReceived(update);
    }

}
