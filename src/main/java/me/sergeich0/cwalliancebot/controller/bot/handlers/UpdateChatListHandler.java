package me.sergeich0.cwalliancebot.controller.bot.handlers;

import lombok.AllArgsConstructor;
import me.sergeich0.cwalliancebot.dto.TelegramChatDTO;
import me.sergeich0.cwalliancebot.service.TelegramChatService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@AllArgsConstructor
public class UpdateChatListHandler implements UpdateHandler {

    private TelegramChatService service;

    @Override
    public void handle(Update update) {
        handleChatOrPrivate(update);
        handleChannel(update);
    }

    private void handleChatOrPrivate(Update update) {
        if (update.hasMessage()) {
            Chat chat = update.getMessage().getChat();
            TelegramChatDTO telegramChat = getTelegramChat(chat);
            service.save(telegramChat);
        }
    }

    private void handleChannel(Update update) {
        if (update.hasChannelPost()) {
            Chat chat = update.getChannelPost().getChat();
            TelegramChatDTO telegramChat = getTelegramChat(chat);
            service.save(telegramChat);
        }
    }

    private TelegramChatDTO getTelegramChat(Chat chat) {
        return TelegramChatDTO.builder()
                .id(chat.getId())
                .type(chat.getType())
                .title(chat.getTitle())
                .firstName(chat.getFirstName())
                .lastName(chat.getLastName())
                .userName(chat.getUserName())
                .build();
    }

}
