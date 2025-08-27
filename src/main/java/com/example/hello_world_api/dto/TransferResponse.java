package com.example.hello_world_api.dto;

import com.example.hello_world_api.entity.Transfer;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class TransferResponse {
    
    private Long id;
    private String fromUserName;
    private String fromMemberId;
    private String toUserName;
    private String toMemberId;
    private Double amount;
    private String description;
    private String status;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    
    public TransferResponse() {}
    
    public TransferResponse(Transfer transfer) {
        this.id = transfer.getId();
        this.fromUserName = transfer.getFromUser().getFirstname() + " " + transfer.getFromUser().getLastname();
        this.fromMemberId = transfer.getFromUser().getMemberId();
        this.toUserName = transfer.getToUser().getFirstname() + " " + transfer.getToUser().getLastname();
        this.toMemberId = transfer.getToUser().getMemberId();
        this.amount = transfer.getAmount();
        this.description = transfer.getDescription();
        this.status = transfer.getStatus().toString();
        this.createdAt = transfer.getCreatedAt();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFromUserName() {
        return fromUserName;
    }
    
    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }
    
    public String getFromMemberId() {
        return fromMemberId;
    }
    
    public void setFromMemberId(String fromMemberId) {
        this.fromMemberId = fromMemberId;
    }
    
    public String getToUserName() {
        return toUserName;
    }
    
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }
    
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
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
