package com.example.kansha.services;

import com.example.kansha.models.Gratitude;
import com.example.kansha.models.User;
import com.example.kansha.repositories.GratitudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GratitudeService {

    @Autowired
    private GratitudeRepository gratitudeRepository;

    public Gratitude createNewGratitude(Gratitude gratitude) {
        return gratitudeRepository.save(gratitude);
    }

    public List<Gratitude> findGratitudesByText(String text) {
        return gratitudeRepository.findByGratitudeText(text);
    }

    public List<Gratitude> findAllGratitudesByUser(User id) {
        return gratitudeRepository.findByUser(id);
    }

    public Optional<Gratitude> findGratitudeByIdAndUser(Integer id, User user) {
        return gratitudeRepository.findByIdAndUser(id, user);
    }

    public void deleteGratitude(Integer id) {
        if (gratitudeRepository.existsById(id)) {
            gratitudeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Gratitude not found with ID: " + id);
        }
    }

    public Gratitude updateGratitude(Gratitude gratitude) {
        if (gratitudeRepository.existsById(gratitude.getId())) {
            return gratitudeRepository.save(gratitude);
        } else {
            throw new RuntimeException("Gratitude not found with ID: " + gratitude.getId());
        }
    }

    public List<Gratitude> allGratitudes() {
        List<Gratitude> gratitudes = new ArrayList<>();
        gratitudeRepository.findAll().forEach(gratitudes::add);
        return gratitudes;
    }
}