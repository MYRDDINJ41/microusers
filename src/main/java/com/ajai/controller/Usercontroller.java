package com.ajai.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajai.model.*;
import com.ajai.respository.*;


@RestController
@RequestMapping("/api")
public class Usercontroller {
	
	@GetMapping("/data")
	public String testData() {
		return "Hola mundo!!";
	}

	@Autowired 
    private UserRepo repo;

    @GetMapping("/findAllUsers")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name){
        try {
            List<User> users = new ArrayList<User>();

            if (name == null) 
                repo.findAll().forEach(users::add);
            else
                repo.findByName(name).forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
            

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        Optional<User> userData = repo.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);            
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            repo.save(user);
            return new ResponseEntity<> (user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);    
        }        
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        Optional<User> userData = repo .findById(id);

        if (userData.isPresent()) {

            User _user = userData.get();
            _user.setName(user.getName());
            _user.setCedula(user.getCedula());
            _user.setNum_phone(user.getNum_phone());
            _user.setEmail(user.getEmail());
            _user.setPassword(user.getPassword());
            _user.setUser(user.getUser());
            
            return new ResponseEntity<>(repo.save(_user), HttpStatus.OK);
            
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<User> deleteAllUsers() {
        try {
            repo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
	
}
