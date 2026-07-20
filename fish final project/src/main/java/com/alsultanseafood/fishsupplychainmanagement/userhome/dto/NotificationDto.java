package com.alsultanseafood.fishsupplychainmanagement.userhome.dto;



public class NotificationDto {

    private String message;

    public NotificationDto() {
    }

    public NotificationDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(
            String message) {
        this.message = message;
    }
}