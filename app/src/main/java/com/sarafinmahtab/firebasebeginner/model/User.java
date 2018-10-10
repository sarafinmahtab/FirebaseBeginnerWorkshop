package com.sarafinmahtab.firebasebeginner.model;

public class User {

    private String username;
    private String email;

    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
