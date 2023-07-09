package com.example.RentalCarProject.DTOs;

import lombok.*;

import java.time.Instant;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ReservationDatesUpdateRequest {
    private Instant startDate;
    private Instant endDate;
}
