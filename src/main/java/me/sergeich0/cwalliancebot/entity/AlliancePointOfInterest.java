package me.sergeich0.cwalliancebot.entity;

import jakarta.persistence.*;
import lombok.*;
import me.sergeich0.cwalliancebot.controller.bot.util.PoiType;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class AlliancePointOfInterest {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String code;

    @Column(nullable=false)
    private PoiType type;

    @Column(nullable=false)
    private String name;

    private Integer level;

    private boolean active;

    public AlliancePointOfInterest(String code, PoiType type, String name, Integer level) {
        this.code = code;
        this.type = type;
        this.name = name;
        this.level = level;
        this.active = true;
    }

}
