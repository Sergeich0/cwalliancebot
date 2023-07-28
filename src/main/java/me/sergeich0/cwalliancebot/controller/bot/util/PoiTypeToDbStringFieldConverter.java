package me.sergeich0.cwalliancebot.controller.bot.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply=true)
public class PoiTypeToDbStringFieldConverter implements AttributeConverter<PoiType, String> {
    @Override
    public String convertToDatabaseColumn(PoiType poiType) {
        if (poiType == null) {
            return null;
        }
        return poiType.getType();
    }

    @Override
    public PoiType convertToEntityAttribute(String poiType) {
        if (poiType == null) {
            return null;
        }
        return Stream.of(PoiType.values())
                .filter(t -> t.getType().equals(poiType))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
