package com.alsultanseafood.fishsupplychainmanagement.user.entity;



import jakarta.persistence.*;

import java.util.List;

import com.alsultanseafood.fishsupplychainmanagement.order.entity.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(
            strategy =
            GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String fullName;

    @Column(
            nullable = false,
            unique = true)
    private String email;

    @Column(
            nullable = false,
            unique = true)
    private String phoneNumber;


    @Column(nullable = false)
private String address;

    @Column(nullable = false)
    private String password;



    /*
       ADMIN
       USER
    */
    @Column(nullable = false)
    private String role;

    /*
       RETAIL
       WHOLESALE
       HOTEL
    */
    private String customerType;

    private Boolean active = true;


    @OneToMany(mappedBy = "user")
@JsonIgnore
private List<Order> orders;

    public User() {
    }

    

    public User( String fullName, String email, String phoneNumber, String address, String password,
            String role, String customerType, Boolean active) {
       
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.role = role;
        this.customerType = customerType;
        this.active = active;
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(
            Long userId) {
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

    public void setEmail(
            String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(
            String phoneNumber) {
        this.phoneNumber =
                phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(
            String password) {
        this.password =
                password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(
            String role) {
        this.role = role;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(
            String customerType) {
        this.customerType =
                customerType;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(
            Boolean active) {
        this.active = active;
    }


    public String getAddress() {
    return address;
}



public void setAddress(
        String address) {
    this.address = address;
}
}


