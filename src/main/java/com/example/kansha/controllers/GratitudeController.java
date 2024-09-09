package com.example.kansha.controllers;

import com.example.kansha.models.Gratitude;
import com.example.kansha.services.GratitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/gratitudes")
public class GratitudeController {

    @Autowired
    private GratitudeService gratitudeService;

    @PostMapping
    public Gratitude createGratitude(@RequestBody Gratitude gratitude){
        return gratitudeService.createNewGratitude(gratitude);
    }

    @GetMapping("/text/{gratitudeText}")
    public ResponseEntity<List<Gratitude>> getGratitudeByText(@PathVariable String gratitudeText){
        List<Gratitude> gratitudes = gratitudeService.findGratitudesByText(gratitudeText);
        return ResponseEntity.ok(gratitudes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gratitude> getById(@PathVariable Integer id) {
        Optional<Gratitude> gratitude = gratitudeService.findGratitudeById(id);
        return gratitude.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public Gratitude updateGratitude(@PathVariable Integer id, @RequestBody Gratitude gratitude){
        gratitude.setId(id);
        return gratitudeService.updateGratitude(gratitude);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Boolean> getAllGratitude(@PathVariable Integer id) {
        gratitudeService.deleteGratitude(id);
        return ResponseEntity.ok(true);
    }
}
