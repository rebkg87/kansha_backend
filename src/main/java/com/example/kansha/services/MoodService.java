package com.example.kansha.services;

import com.example.kansha.models.Mood;
import com.example.kansha.repositories.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoodService {

    @Autowired
    private MoodRepository moodRepository;

    public Mood findByName(String name) { return moodRepository.findByName(name); }

}
