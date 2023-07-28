package me.sergeich0.cwalliancebot.converter;

import me.sergeich0.cwalliancebot.dto.AlliancePointOfInterestDTO;
import me.sergeich0.cwalliancebot.entity.AlliancePointOfInterest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlliancePointOfInterestConverter implements Converter<AlliancePointOfInterest, AlliancePointOfInterestDTO> {
    @Override
    public AlliancePointOfInterest toEntity(AlliancePointOfInterestDTO dto) {
        if (dto == null) {
            return null;
        }
        return AlliancePointOfInterest.builder()
                .id(dto.getId())
                .type(dto.getType())
                .level(dto.getLevel())
                .name(dto.getName())
                .code(dto.getCode())
                .active(dto.isActive()).build();
    }

    @Override
    public AlliancePointOfInterestDTO toDTO(AlliancePointOfInterest entity) {
        if (entity == null) {
            return null;
        }
        return AlliancePointOfInterestDTO.builder()
                .id(entity.getId())
                .type(entity.getType())
                .level(entity.getLevel())
                .name(entity.getName())
                .code(entity.getCode())
                .active(entity.isActive()).build();
    }

    @Override
    public List<AlliancePointOfInterest> toEntityList(List<AlliancePointOfInterestDTO> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .toList();
    }

    @Override
    public List<AlliancePointOfInterestDTO> toDTOList(List<AlliancePointOfInterest> entityList) {
        return entityList.stream()
                .map(this::toDTO)
                .toList();
    }
}
