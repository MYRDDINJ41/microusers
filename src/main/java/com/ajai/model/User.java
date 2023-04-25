package com.ajai.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection =  "users")
public class User {

	// id
    // name
    // cedula
    // num_phone
    // email
    // user
    // password

    @Id
    private int id;
    
    private String name;
    private Long cedula;
    private Long num_phone;
    private String email;
    private String user;
    private String password;

    public User() {

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

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public Long getNum_phone() {
        return num_phone;
    }

    public void setNum_phone(Long num_phone) {
        this.num_phone = num_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int id, String name, Long cedula, Long num_phone, String email, String user, String password) {
        this.id = id;
        this.name = name;
        this.cedula = cedula;
        this.num_phone = num_phone;
        this.email = email;
        this.user = user;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users [id " + id + ", name " + name + ", cedula " + cedula + ", num_phone " + num_phone + ", email " + email +", user " + user + ", password " + password + "]";
    }
	
}
