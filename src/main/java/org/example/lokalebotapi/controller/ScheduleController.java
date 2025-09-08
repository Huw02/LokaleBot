package org.example.lokalebotapi.controller;
import com.fasterxml.jackson.databind.JsonNode;
import org.example.lokalebotapi.service.ScheduleScraper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

    private final ScheduleScraper scheduleScraper;

    public ScheduleController(ScheduleScraper scheduleScraper) {
        this.scheduleScraper = scheduleScraper;
    }

    @GetMapping("/schedule")
    public JsonNode getSchedule() {
        JsonNode schedule = scheduleScraper.getLatestSchedule();
        if (schedule == null) {
            return scheduleScraper.getLatestSchedule(); // returner null hvis ikke scraped endnu
        }
        return schedule;
    }
}
