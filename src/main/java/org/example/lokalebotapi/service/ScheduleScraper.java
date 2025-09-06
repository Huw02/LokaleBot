package org.example.lokalebotapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Service
public class ScheduleScraper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Variabel til at gemme seneste skema
    private JsonNode latestSchedule;

    public JsonNode getLatestSchedule() {
        return latestSchedule;
    }

    // Kører hver dag kl. 06:55 (cron format: sekund minut time dag måned ugedag)
    @Scheduled(cron = "0 55 6 * * MON-FRI")
    public void scrapeSchedule() {
        try {
            ProcessBuilder pb = new ProcessBuilder("python3", "src/main/resources/scraper/schedule_scraper.py");
            pb.redirectErrorStream(true);
            Process process = pb.start();

            String result = new BufferedReader(new InputStreamReader(process.getInputStream()))
                    .lines().collect(Collectors.joining());

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("❌ Scraper fejlede");
                return;
            }

            latestSchedule = objectMapper.readTree(result);
            System.out.println("✅ Skema scraped kl. 06:55");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
