package com.example.kansha.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Table(name="user_mood")
@Entity
public class UserMood {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "mood_id", nullable = false)
    private Mood mood;

    @Column(name = "mood_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date moodDate;

    public UserMood(){}

    public UserMood(Integer id, User user, Mood mood, Date moodDate) {
        this.id = id;
        this.user = user;
        this.mood = mood;
        this.moodDate = moodDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public Date getMoodDate() {
        return moodDate;
    }

    public void setMoodDate(Date moodDate) {
        this.moodDate = moodDate;
    }
}
