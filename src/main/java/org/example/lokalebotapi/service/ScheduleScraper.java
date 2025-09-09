package org.example.lokalebotapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

@Service
public class ScheduleScraper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private JsonNode latestSchedule;

    public JsonNode getLatestSchedule() {
        return latestSchedule;
    }

    @Scheduled(cron = "0 20 18 * * MON-FRI") // Juster tidspunkt her
    public void scrapeSchedule() {
        try {
            // Absolut sti til Python
            //String pythonPath = "C:\\Users\\Hanni\\AppData\\Local\\Programs\\Python\\Python313\\python.exe";
            String pythonPath = "python3";
            String scriptPath = "src/main/resources/scraper/main.py";

            ProcessBuilder pb = new ProcessBuilder(pythonPath, scriptPath);

            // Tilføj environment variables hvis nødvendigt
            Map<String, String> env = pb.environment();
            env.put("SOME_ENV_VAR", "value"); // Eksempel

            pb.redirectErrorStream(true); // Saml stdout og stderr

            // Sæt working directory til script-mappen
            pb.directory(new File("src/main/resources/scraper"));

            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String jsonOutput = null;

            while ((line = reader.readLine()) != null) {
                System.out.println("[Python] " + line); // debug output

                // Hvis linjen starter med [ eller { → antag JSON
                if (line.startsWith("[") || line.startsWith("{")) {
                    jsonOutput = line;
                }
            }

            int exitCode = process.waitFor();
            System.out.println("Python exit code: " + exitCode);

            if (exitCode != 0) {
                System.err.println("❌ Python-script fejlede");
                return;
            }

            if (jsonOutput != null) {
                latestSchedule = objectMapper.readTree(jsonOutput);
                System.out.println("✅ Skema scraped korrekt");
            } else {
                System.err.println("❌ Ingen JSON output fra Python-scriptet");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
