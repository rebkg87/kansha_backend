package com.example.kansha.repositories;

import com.example.kansha.models.Gratitude;
import com.example.kansha.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GratitudeRepository extends JpaRepository<Gratitude, Integer> {
    List<Gratitude> findByGratitudeText (String gratitudeText);
    List<Gratitude> findByUser(User user);
    Gratitude getById(Integer id);
}
