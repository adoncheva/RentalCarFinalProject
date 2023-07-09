package com.example.RentalCarProject.DTOs;

import com.example.RentalCarProject.Entities.Car;
import com.example.RentalCarProject.Entities.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReservationResponseByCar {
    private Long id;
    private User user;
    private String startDate;
    private String endDate;
    private Integer period;
    private Double price;
}
