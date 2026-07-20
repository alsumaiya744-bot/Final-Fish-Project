package com.alsultanseafood.fishsupplychainmanagement.dashboard.dto;

public class ActivityDto {

    private String activity;
    private String date;

    public ActivityDto() {
    }

    public ActivityDto(
            String activity,
            String date) {

        this.activity = activity;
        this.date = date;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(
            String activity) {
        this.activity = activity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(
            String date) {
        this.date = date;
    }
}
