package me.sergeich0.cwalliancebot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.sergeich0.cwalliancebot.controller.bot.util.PoiType;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AlliancePointOfInterestDTO {
    private long id;
    private String code;
    private PoiType type;
    private String name;
    private Integer level;
    private boolean active;

    public AlliancePointOfInterestDTO(String code, PoiType type, String name, Integer level) {
        this.code = code;
        this.type = type;
        this.name = name;
        this.level = level;
        this.active = true;
    }
}
