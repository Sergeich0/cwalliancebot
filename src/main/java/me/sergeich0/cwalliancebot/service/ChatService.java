package me.sergeich0.cwalliancebot.service;

import me.sergeich0.cwalliancebot.dto.TelegramChatDTO;

import java.util.List;

public interface ChatService {
    void save(TelegramChatDTO chat);

    TelegramChatDTO getChat(Long id);

    List<TelegramChatDTO> getAllChats();

    List<TelegramChatDTO> getInactiveChats();
}
