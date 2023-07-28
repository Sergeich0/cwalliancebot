package me.sergeich0.cwalliancebot.service;

import lombok.AllArgsConstructor;
import me.sergeich0.cwalliancebot.converter.AlliancePointOfInterestConverter;
import me.sergeich0.cwalliancebot.dto.AlliancePointOfInterestDTO;
import me.sergeich0.cwalliancebot.entity.AlliancePointOfInterest;
import me.sergeich0.cwalliancebot.repository.PoiRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class AlliancePoiService implements PoiService {

    private PoiRepository repository;
    private AlliancePointOfInterestConverter converter;

    @Transactional
    @Override
    public void save(AlliancePointOfInterestDTO poi) {
        repository.save(converter.toEntity(poi));
    }

    @Override
    public Map<String, List<AlliancePointOfInterestDTO>> getPoiByLevels(int... levels) {
        List<AlliancePointOfInterest> allPoiList = repository.findAllPoi();

        Map<String, List<AlliancePointOfInterestDTO>> resultMap = new LinkedHashMap<>();

        for (int i = 0; i < levels.length; i++) {
            int level = levels[i];
            List<AlliancePointOfInterest> poiList = allPoiList.stream()
                    .filter(poi -> poi.getLevel() < level)
                    .toList();
            if (!poiList.isEmpty()) { // Empty lists aren't needed
                // Name for list
                String levelsDiapason = levels[i - 1] + "-" + (level - 1);

                // Add name, list
                resultMap.put(levelsDiapason, converter.toDTOList(poiList));
            }

            // Remove collected POIs from full list
            allPoiList.removeAll(poiList);
        }
        // Add remaining POIs
        if (!allPoiList.isEmpty()) {
            resultMap.put(levels[levels.length - 1] + "+", converter.toDTOList(allPoiList));
        }

        return resultMap;
    }

    @Override
    public List<AlliancePointOfInterestDTO> getHeadquarters() {
        return converter.toDTOList(repository.getHeadquarters());
    }

    @Override
    public AlliancePointOfInterestDTO getPoiByCode(String poiCode) {
        return converter.toDTO(repository.getPoiByCode(poiCode));
    }
}
