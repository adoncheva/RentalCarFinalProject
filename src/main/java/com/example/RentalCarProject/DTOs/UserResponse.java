package com.example.RentalCarProject.DTOs;

import com.example.RentalCarProject.Entities.Car;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserResponse {


    private long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;


}
