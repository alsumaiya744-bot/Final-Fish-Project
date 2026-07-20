package com.alsultanseafood.fishsupplychainmanagement.dashboard.entity;



import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private Long activityId;

    private String activity;

    private LocalDateTime date;

    public Activity() {
    }

    public Activity(
            String activity,
            LocalDateTime date) {

        this.activity = activity;
        this.date = date;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(
            Long activityId) {
        this.activityId = activityId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(
            String activity) {
        this.activity = activity;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(
            LocalDateTime date) {
        this.date = date;
    }
}