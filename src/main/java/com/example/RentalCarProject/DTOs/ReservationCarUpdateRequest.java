package com.example.RentalCarProject.DTOs;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ReservationCarUpdateRequest {
    private Long carId;
}
