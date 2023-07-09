package com.example.RentalCarProject.Controllers;

import com.example.RentalCarProject.DTOs.UserPasswordRequest;
import com.example.RentalCarProject.DTOs.UserRequest;
import com.example.RentalCarProject.DTOs.UserResponse;
import com.example.RentalCarProject.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path="/register")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserRequest request){

      UserResponse userResponse =  userService.saveUser(request);
      String response = String.format("User with name %s %s was created with %s id",
              userResponse.getFirstName(), userResponse.getLastName(),userResponse.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById (@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.FOUND).body(userService.getUser(id));
    }
    @GetMapping("/find")
    public ResponseEntity<UserResponse> getUserByEmail (@RequestParam String email){
        return  ResponseEntity.status(HttpStatus.FOUND).body(userService.getByEmail(email));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedUser(@PathVariable Long id){
        userService.deletedUser(id);
        String response = String.format("User was deleted with %s id",id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
    @PutMapping(path="/update/{id}")
    public ResponseEntity<UserResponse> updatedUser(@PathVariable Long id, @RequestBody UserRequest userRequest ){

        UserResponse userResponse=userService.updatedUser(id,userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
    @PutMapping(path="/updatePass/{id}")
    public ResponseEntity<String> updatedPasswordUser(@PathVariable Long id, @RequestBody UserPasswordRequest userPasswordRequest){

        UserResponse userResponse=userService.updatedUserPassword(id,userPasswordRequest);
        String response = String.format("Password of user with %s id was updated",id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
