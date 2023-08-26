package me.sergeich0.cwalliancebot.converter;

import me.sergeich0.cwalliancebot.dto.TelegramChatDTO;
import me.sergeich0.cwalliancebot.entity.TelegramChat;
import org.springframework.stereotype.Component;

@Component
public class TelegramChatConverter implements Converter<TelegramChat, TelegramChatDTO> {
    @Override
    public TelegramChat toEntity(TelegramChatDTO dto) {
        if (dto == null) {
            return null;
        }
        return TelegramChat.builder()
                .id(dto.getId())
                .type(dto.getType())
                .title(dto.getTitle())
                .userName(dto.getUserName())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .active(dto.isActive())
                .build();
    }

    @Override
    public TelegramChatDTO toDTO(TelegramChat entity) {
        if (entity == null) {
            return null;
        }
        return TelegramChatDTO.builder()
                .id(entity.getId())
                .type(entity.getType())
                .title(entity.getTitle())
                .userName(entity.getUserName())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .active(entity.isActive())
                .build();
    }

}
