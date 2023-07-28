package me.sergeich0.cwalliancebot.repository;

import me.sergeich0.cwalliancebot.entity.AlliancePointOfInterest;

import java.util.List;

public interface PoiRepository {
    void save(AlliancePointOfInterest poi);

    List<AlliancePointOfInterest> findAllPoi();

    List<AlliancePointOfInterest> getHeadquarters();

    AlliancePointOfInterest getPoiByCode(String poiCode);
}
