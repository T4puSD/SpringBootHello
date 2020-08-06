package com.example.demo;

public class UserNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public UserNotFoundException(Integer id){
        super(String.format("User not found with id: %d",id));
    }
}