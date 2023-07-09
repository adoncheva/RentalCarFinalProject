package com.example.RentalCarProject.DTOs;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CarUpdateResponse extends CarResponse {
    public CarUpdateResponse(long id, @NotBlank String brand, @NotBlank String model, Integer numberOfSeats, @NotBlank Double pricePerDay) {
        super(id, brand, model, numberOfSeats, pricePerDay);
    }

    public CarUpdateResponse() {
    }
}




