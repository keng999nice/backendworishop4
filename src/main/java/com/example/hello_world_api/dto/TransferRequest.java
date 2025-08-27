package com.example.hello_world_api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TransferRequest {
    
    @NotBlank(message = "Recipient member ID is required")
    private String toMemberId;
    
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;
    
    private String description;
    
    public TransferRequest() {}
    
    public TransferRequest(String toMemberId, Double amount, String description) {
        this.toMemberId = toMemberId;
        this.amount = amount;
        this.description = description;
    }
    
    // Getters and Setters
    public String getToMemberId() {
        return toMemberId;
    }
    
    public void setToMemberId(String toMemberId) {
        this.toMemberId = toMemberId;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
