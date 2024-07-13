package com.management.JournalService.model;


import lombok.Data;

@Data
public class UserEvent {
    private Long userId;
    private String action;

    private String role;

    public Long getUserId() {
        return userId;
    }

    // Setter for userId
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Getter for action
    public String getAction() {
        return action;
    }

    // Setter for action
    public void setAction(String action) {
        this.action = action;
    }
    public String getRole() {
        return role;
    }

    // Setter for action
    public void setRole(String role) {
        this.role = role;
    }

}