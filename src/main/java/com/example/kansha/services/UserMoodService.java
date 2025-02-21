package com.example.kansha.services;

import com.example.kansha.models.Mood;
import com.example.kansha.models.User;
import com.example.kansha.models.UserMood;
import com.example.kansha.repositories.UserMoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserMoodService {

    @Autowired
    private UserMoodRepository userMoodRepository;

    public List<UserMood> findByUserAndMoodDate (User user, Date moodDate) {
        return userMoodRepository.findByUserAndMoodDate(user, moodDate);
    }

    public List<UserMood>findByUserAndMood(User user, Mood mood) {
        return  userMoodRepository.findByUserAndMood(user, mood);
    }

    public UserMood createNewUserMood (User user, Mood mood, Date moodDate){
        UserMood userMood = new UserMood();
        userMood.setUser(user);
        userMood.setMood(mood);
        userMood.setMoodDate(moodDate);

        return userMoodRepository.save(userMood);
    }

}
