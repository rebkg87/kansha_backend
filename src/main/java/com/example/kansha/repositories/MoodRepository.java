package com.example.kansha.repositories;

import com.example.kansha.models.Mood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Integer> {
    Mood findByName(String name);
}
