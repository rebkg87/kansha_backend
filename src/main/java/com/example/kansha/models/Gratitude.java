package com.example.kansha.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Table(name= "gratitude" )
@Entity
public class Gratitude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "gratitude_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date gratitudeDate;

    @Column(name = "gratitude_text", columnDefinition = "TEXT")
    private String gratitudeText;

    public Gratitude() {
    }

    public Gratitude(User user, Date gratitudeDate, String gratitudeText, Integer id) {
        this.user = user;
        this.gratitudeDate = gratitudeDate;
        this.gratitudeText = gratitudeText;
        this.id = id;
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

    public Date getGratitudeDate() {
        return gratitudeDate;
    }

    public void setGratitudeDate(Date gratitudeDate) {
        this.gratitudeDate = gratitudeDate;
    }

    public String getGratitudeText() {
        return gratitudeText;
    }

    public void setGratitudeText(String gratitudeText) {
        this.gratitudeText = gratitudeText;
    }
}
