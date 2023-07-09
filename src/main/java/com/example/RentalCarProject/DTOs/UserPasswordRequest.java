package com.example.RentalCarProject.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserPasswordRequest {
    @NotBlank
    private String pwd;
}
