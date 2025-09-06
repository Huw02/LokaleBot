package org.example.lokalebotapi.controller;

import org.example.lokalebotapi.model.Lokale;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api")
public class ScrapingController {


    List<Lokale>tempListe = new ArrayList<>();

    @PostMapping("/scraped")
    public ResponseEntity<Void> receiveScrapedData(@RequestBody List<Lokale> data){
        tempListe.clear();
        tempListe.addAll(data);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/scraped")
    public ResponseEntity<List<Lokale>> getReceiveScrapedData(){
        return ResponseEntity.ok(tempListe);
    }

}
