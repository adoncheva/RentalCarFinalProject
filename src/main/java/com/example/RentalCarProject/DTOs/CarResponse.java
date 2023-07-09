package com.example.RentalCarProject.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CarResponse {


    private long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    private Integer numberOfSeats;

    @NotBlank
    private Double pricePerDay;
}
