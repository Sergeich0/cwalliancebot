package me.sergeich0.cwalliancebot.controller;

import lombok.AllArgsConstructor;
import me.sergeich0.cwalliancebot.dto.AlliancePointOfInterestDTO;
import me.sergeich0.cwalliancebot.service.AlliancePoiService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class PoiController {

    private final AlliancePoiService poiService;

    @PreAuthorize("hasRole('OFFICER')")
    @GetMapping("/poi")
    public String getPoiList(Model model) {
        Map<String, List<AlliancePointOfInterestDTO>> pois = poiService.getPoiByLevels(20, 40, 60);
        model.addAttribute("pois", pois);
        return "poi_list";
    }

    @PreAuthorize("hasRole('OFFICER')")
    @GetMapping("/headquarters")
    public String getHeadquarterList(Model model) {
        List<AlliancePointOfInterestDTO> pois = poiService.getHeadquarters();
        model.addAttribute("pois", pois);
        return "headquarters_list";
    }

    @PreAuthorize("hasRole('OFFICER')")
    @GetMapping("/poi/{code}")
    public String getPoiInfo(Model model, @PathVariable String code) {
        AlliancePointOfInterestDTO poi = poiService.getPoiByCode(code);
        model.addAttribute("poi", poi);
        return "poi_info";
    }

    @PreAuthorize("hasRole('OFFICER')")
    @GetMapping("/poi/{code}/deactivate")
    public String deactivatePoi(@PathVariable String code) {
        AlliancePointOfInterestDTO poi = poiService.getPoiByCode(code);
        poi.setActive(false);
        poiService.save(poi);
        return "redirect:/poi/{code}";
    }

    @PreAuthorize("hasRole('OFFICER')")
    @GetMapping("/poi/{code}/activate")
    public String activatePoi(@PathVariable String code) {
        AlliancePointOfInterestDTO poi = poiService.getPoiByCode(code);
        poi.setActive(true);
        poiService.save(poi);
        return "redirect:/poi/{code}";
    }

}
