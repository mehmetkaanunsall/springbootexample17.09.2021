
package com.springbootexample.backend.api;

import com.springbootexample.backend.dto.UserCreateDTO;
import com.springbootexample.backend.dto.UserUpdateDTO;
import com.springbootexample.backend.dto.UserViewDTO;
import com.springbootexample.backend.service.UserService;
import com.springbootexample.backend.shared.GenericResponse;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserAPI {
    
    private final UserService userService;
    
    @GetMapping("v1/user/{id}")
    public ResponseEntity<UserViewDTO> getUserById(@PathVariable Long id)
    {
          final UserViewDTO userViewDTO = userService.getUserById(id);
        return ResponseEntity.ok(userViewDTO);
    }
    
    @GetMapping("v1/users")
    public ResponseEntity<List <UserViewDTO>> getUsers()
    {
        final List<UserViewDTO> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
    
    @PostMapping("v1/user")
    public ResponseEntity<?> createUser (@Valid @RequestBody UserCreateDTO userCreateDTO )
    {
        userService.createUser(userCreateDTO);
      return ResponseEntity.ok(new GenericResponse("User Created"));  
    }
    
    @PutMapping("v1/user/{id}")
    public ResponseEntity<UserViewDTO>  updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateDTO userUpdateDTO )
    {
        final UserViewDTO userViewDTO = userService.updateUser(id,userUpdateDTO);
        return ResponseEntity.ok(userViewDTO);
    }
    
    @DeleteMapping("v1/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id)
    {
         userService.deleteUser(id);
         return ResponseEntity.ok(new GenericResponse("User Deleted"));
    }
    
    
    @GetMapping("v1/user/slice")
    public ResponseEntity<List<UserViewDTO>> slice (Pageable pageable)
    {
        final List<UserViewDTO> userViewDTOs= userService.slice(pageable);
        return ResponseEntity.ok(userViewDTOs);
    }
    
}
