package com.example.kansha.repositories;

import com.example.kansha.models.Gratitude;
import com.example.kansha.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GratitudeRepository extends JpaRepository<Gratitude, Integer> {
    List<Gratitude> findByGratitudeText (String gratitudeText);
    List<Gratitude> findByUser(User user);
    Optional<Gratitude> findById(Integer id);
    Optional<Gratitude> findByIdAndUser(Integer id, User user);
    List<Gratitude> findAll ();

}
