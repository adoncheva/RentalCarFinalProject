package com.example.RentalCarProject.DTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CarRequest {


    private Long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;
    @Column(columnDefinition = "integer default 4")
    private Integer numberOfSeats;

    @NotNull
    private Double pricePerDay;


}
