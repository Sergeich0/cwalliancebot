package me.sergeich0.cwalliancebot.service;

import me.sergeich0.cwalliancebot.dto.AlliancePointOfInterestDTO;

import java.util.List;
import java.util.Map;

public interface PoiService {
    void save(AlliancePointOfInterestDTO poi);

    Map<String, List<AlliancePointOfInterestDTO>> getPoiByLevels(int... levels);

    List<AlliancePointOfInterestDTO> getHeadquarters();

    AlliancePointOfInterestDTO getPoiByCode(String poiCode);
}
