package com.example.kansha.controllers;

import com.example.kansha.models.Mood;
import com.example.kansha.repositories.MoodRepository;
import com.example.kansha.services.MoodService;
import com.example.kansha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/mood")
@RestController
public class MoodController {

    @Autowired
    private MoodRepository moodRepository;

    @Autowired
    private UserService userService;




}
