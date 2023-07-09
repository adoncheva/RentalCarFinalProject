package com.example.RentalCarProject.DTOs;

import com.example.RentalCarProject.Entities.Car;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class ReservationResponseUser {
    private Long id;
    private Car car;
    private String startDate;
    private String endDate;
    private Integer period;
    private Double price;
}
