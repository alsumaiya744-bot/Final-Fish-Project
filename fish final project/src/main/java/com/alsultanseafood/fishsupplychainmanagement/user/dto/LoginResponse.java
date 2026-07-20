package com.alsultanseafood.fishsupplychainmanagement.user.dto;



public class LoginResponse {

    private String token;
    private String role;
    private Long userId;
    private String fullName;
    private String email;
private String phoneNumber;
private String address;

    public LoginResponse() {
    }

    public LoginResponse(
            String token,
            String role,
            Long userId,
            String fullName,String email,
        String phoneNumber,
        String address) {

        this.token = token;
        this.role = role;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
    this.phoneNumber = phoneNumber;
    this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(
            String fullName) {
        this.fullName = fullName;
    }



    public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPhoneNumber() {
    return phoneNumber;
}

public void setPhoneNumber(
        String phoneNumber) {
    this.phoneNumber = phoneNumber;
}

public String getAddress() {
    return address;
}

public void setAddress(
        String address) {
    this.address = address;
}
}
