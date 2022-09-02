package com.example.visitormanagementapp;

public class User {
    public String fname, lname, password, email;
    public User(){

    }
    public User(String fname,String lname,String password,String email){
        this.fname = fname;
        this.lname =lname;
        this.password = password;
        this.email =email;
    }
}
