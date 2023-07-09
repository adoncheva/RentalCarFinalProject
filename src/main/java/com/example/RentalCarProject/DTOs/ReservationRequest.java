package com.example.RentalCarProject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationRequest {
    private Long userId;
    private Long carId;
    private Instant startDate;
    private Instant endDate;

}
