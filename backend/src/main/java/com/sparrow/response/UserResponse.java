package com.sparrow.response;

import com.sparrow.model.user.User;

public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean enabled;
    private String role;

    public UserResponse() {
    }

    public UserResponse(Long id, String email, String firstName, String lastName, Boolean enabled, String role) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.role = role;
    }

    public UserResponse(User user) {
        this(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName()
                , user.getEnabled(), user.getRole().getName());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}