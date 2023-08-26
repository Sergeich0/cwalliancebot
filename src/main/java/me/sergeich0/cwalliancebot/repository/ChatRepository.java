package me.sergeich0.cwalliancebot.repository;

import me.sergeich0.cwalliancebot.entity.TelegramChat;

import java.util.List;


public interface ChatRepository {
    void save(TelegramChat chat);

    TelegramChat getChat(Long id);

    List<TelegramChat> getAllChats();

    List<TelegramChat> getInactiveChats();
}
