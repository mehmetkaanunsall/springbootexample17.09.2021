/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootexample.backend.service;

import com.springbootexample.backend.dto.UserCreateDTO;
import com.springbootexample.backend.dto.UserUpdateDTO;
import com.springbootexample.backend.dto.UserViewDTO;
import com.springbootexample.backend.exception.NotFoundException;
import com.springbootexample.backend.model.User;
import com.springbootexample.backend.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor//Consructor oluşturuyor
@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public UserViewDTO getUserById(Long id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("NotFoundException"));
        return UserViewDTO.of(user);
    }

    @Override
    @Transactional
    public UserViewDTO createUser(UserCreateDTO userCreateDTO) {

        final User user = userRepository.save(new User(userCreateDTO.getUserName(),userCreateDTO.getFirstName(), userCreateDTO.getLastName()));
        return UserViewDTO.of(user);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<UserViewDTO> getUsers() {
       return userRepository.findAll().stream().map(UserViewDTO::of).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserViewDTO updateUser(Long id,UserUpdateDTO userUpadateDTO) {
        final User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("NotFoundException"));
        
        user.setFirstName(userUpadateDTO.getFirstName());
        user.setLastName(userUpadateDTO.getLastName());
        final User updatedUser = userRepository.save(user);
        return UserViewDTO.of(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        
        final User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("UserNotFoundException"));
        userRepository.deleteById(user.getId());
        
    }

    @Override
    @Transactional(readOnly = true ,propagation = Propagation.SUPPORTS)
    public List<UserViewDTO> slice(Pageable pgbl) {
        return userRepository.findAll(pgbl).stream().map(UserViewDTO::of).collect(Collectors.toList());
    }

}
