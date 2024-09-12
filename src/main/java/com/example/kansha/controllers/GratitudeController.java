package com.example.kansha.controllers;

import com.example.kansha.models.Gratitude;
import com.example.kansha.models.User;
import com.example.kansha.services.GratitudeService;
import com.example.kansha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gratitudes")
public class GratitudeController {

    @Autowired
    private GratitudeService gratitudeService;

    @Autowired
    private UserService userService;

    //Create
    @PostMapping("")
    public ResponseEntity<Gratitude> createGratitude(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody Gratitude gratitude){
        Optional<User> userOptional = userService.findByEmail(userDetails.getUsername());
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(403).build();
        }
        User authenticatedUser = userOptional.get();
        gratitude.setUser(authenticatedUser);

        Gratitude createdGratitude = gratitudeService.createNewGratitude(gratitude);
        return ResponseEntity.ok(createdGratitude);    }


    //Read
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Gratitude>> getGratitudesByUser(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer userId) {
        Optional<User> userOptional = userService.findByEmail(userDetails.getUsername());
        if (!userOptional.isPresent() || !userOptional.get().getId().equals(userId)) {
            return ResponseEntity.status(403).build();
        }
        List<Gratitude> gratitudes = gratitudeService.findAllGratitudesByUser(userOptional.get());
        return ResponseEntity.ok(gratitudes);
    }

    @GetMapping("/")
    public ResponseEntity<List<Gratitude>> allGratitudes() {
        List<Gratitude> gratitudes = gratitudeService.allGratitudes();
        return ResponseEntity.ok(gratitudes);
    }


    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Gratitude> updateGratitude(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer id,
            @RequestBody Gratitude gratitude) {

        Optional<User> userOptional = userService.findByEmail(userDetails.getUsername());
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(403).build();
        }

        User authenticatedUser = userOptional.get();

        Optional<Gratitude> existingGratitude = gratitudeService.findGratitudeByIdAndUser(id, authenticatedUser);
        if (!existingGratitude.isPresent()) {
            return ResponseEntity.status(403).build();
        }

        gratitude.setId(id);
        gratitude.setUser(authenticatedUser);
        Gratitude updatedGratitude = gratitudeService.updateGratitude(gratitude);
        return ResponseEntity.ok(updatedGratitude);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteGratitude(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer id) {

        Optional<User> userOptional = userService.findByEmail(userDetails.getUsername());
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(403).build();
        }

        User authenticatedUser = userOptional.get();
        Optional<Gratitude> existingGratitude = gratitudeService.findGratitudeByIdAndUser(id, authenticatedUser);
        if (!existingGratitude.isPresent()) {
            return ResponseEntity.status(403).build();
        }

        gratitudeService.deleteGratitude(id);
        return ResponseEntity.ok(true);
    }
}
