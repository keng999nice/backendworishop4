package com.example.hello_world_api.dto;

import com.example.hello_world_api.entity.User;

public class ContactResponse {
    
    private Long id;
    private String memberId;
    private String name;
    private String phoneNumber;
    private String email;
    
    public ContactResponse() {}
    
    public ContactResponse(User user) {
        this.id = user.getId();
        this.memberId = user.getMemberId();
        this.name = user.getFirstname() + " " + user.getLastname();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getMemberId() {
        return memberId;
    }
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
