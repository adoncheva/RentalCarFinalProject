package com.example.RentalCarProject.Convertors;

import com.example.RentalCarProject.DTOs.UserRequest;
import com.example.RentalCarProject.DTOs.UserResponse;
import com.example.RentalCarProject.Entities.User;
import org.springframework.stereotype.Component;

@Component

public class UserConverter {

    public User toUser (UserRequest request){
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .pwd(request.getPwd())
                .build();
    }

    public UserResponse toUserResponse(User user){

        return new UserResponse(user.getId(), user.getFirstName(),
                user.getLastName(), user.getEmail());
    }

}
