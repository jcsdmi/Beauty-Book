package com.example.beautybook;
public class User {

    public  String username,password;

    public User(){
        // Default constructor
    }
    public User(String etUsername, String etPassword){
        this.username=etUsername;
        this.password=etPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
