package com.example.RentalCarProject.Services;
import com.example.RentalCarProject.DTOs.UserPasswordRequest;
import com.example.RentalCarProject.DTOs.UserRequest;
import com.example.RentalCarProject.DTOs.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponse saveUser(UserRequest request);

    UserResponse getUser(Long id);

    void deletedUser(Long id);

    UserResponse updatedUser(Long id, UserRequest userRequest);
    UserResponse getByEmail(String email);
    UserResponse updatedUserPassword(Long id, UserPasswordRequest userPasswordRequest);
}
