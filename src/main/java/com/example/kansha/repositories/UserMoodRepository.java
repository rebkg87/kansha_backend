package com.example.kansha.repositories;

import com.example.kansha.models.Mood;
import com.example.kansha.models.User;
import com.example.kansha.models.UserMood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserMoodRepository extends JpaRepository<UserMood, Integer>{
    List<UserMood>findByUserAndMoodDate(User user, Date moodDate);
    List<UserMood>findByUserAndMood(User user, Mood mood);
}
