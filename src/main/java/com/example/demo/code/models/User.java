package com.example.demo.code.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class User {
   private int id;

   @NotEmpty(message = "Name should  not be empty")
   @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
   private String name;

   @NotEmpty(message = "Email should  not be empty")
   @Email(message = "Email should be valid")
   private String email;

   public User(){

   }

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
