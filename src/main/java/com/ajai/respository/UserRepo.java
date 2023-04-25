package com.ajai.respository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ajai.model.*;

public interface UserRepo extends MongoRepository<User, Integer> {
    
    List<User> findByName(String name);
    List<User> findByCedula(Long cedula);

}