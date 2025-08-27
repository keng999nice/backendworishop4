package com.example.hello_world_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Schema(description = "User registration request")
public class RegisterRequest {
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Schema(description = "User email", example = "user@example.com")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Schema(description = "User password", example = "password123")
    private String password;
    
    @NotBlank(message = "First name is required")
    @Schema(description = "User first name", example = "John")
    private String firstname;
    
    @NotBlank(message = "Last name is required")
    @Schema(description = "User last name", example = "Doe")
    private String lastname;
    
    @NotBlank(message = "Phone number is required")
    @Schema(description = "User phone number", example = "0812345678")
    private String phoneNumber;
    
    @Schema(description = "User birthday", example = "1990-01-01")
    private LocalDate birthday;
    
    // Constructors
    public RegisterRequest() {}
    
    public RegisterRequest(String email, String password, String firstname, String lastname, String phoneNumber, LocalDate birthday) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }
    
    // Getters and Setters
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFirstname() {
        return firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastname() {
        return lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public LocalDate getBirthday() {
        return birthday;
    }
    
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
