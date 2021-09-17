/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootexample.backend.service;

import com.springbootexample.backend.dto.UserCreateDTO;
import com.springbootexample.backend.dto.UserUpdateDTO;
import com.springbootexample.backend.dto.UserViewDTO;
import java.util.List;
import org.springframework.data.domain.Pageable;


/**
 *
 * @author kaan.unsal
 */
public interface UserService {
    
    UserViewDTO getUserById( Long id);
    
    UserViewDTO createUser( UserCreateDTO userCreateDTO);

    List<UserViewDTO> getUsers();
    
    UserViewDTO updateUser (Long id,UserUpdateDTO userUpadateDTO);
    
    void deleteUser (Long id);
    
    List<UserViewDTO> slice(Pageable pageable);
    
    
}
