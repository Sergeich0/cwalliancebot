package me.sergeich0.cwalliancebot.service;

import lombok.AllArgsConstructor;
import me.sergeich0.cwalliancebot.converter.TelegramChatConverter;
import me.sergeich0.cwalliancebot.dto.TelegramChatDTO;
import me.sergeich0.cwalliancebot.repository.ChatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class TelegramChatService implements ChatService {

    private ChatRepository repository;
    private TelegramChatConverter converter;

    @Transactional
    @Override
    public void save(TelegramChatDTO chat) {
        repository.save(converter.toEntity(chat));
    }

    @Override
    public TelegramChatDTO getChat(Long id) {
        return converter.toDTO(repository.getChat(id));
    }

    @Override
    public List<TelegramChatDTO> getAllChats() {
        return converter.toDTOList(repository.getAllChats());
    }

    @Override
    public List<TelegramChatDTO> getInactiveChats() {
        return converter.toDTOList(repository.getInactiveChats());
    }
}
