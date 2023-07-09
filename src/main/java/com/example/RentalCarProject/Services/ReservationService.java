package com.example.RentalCarProject.Services;

import com.example.RentalCarProject.DTOs.*;
import com.example.RentalCarProject.Entities.Reservation;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;
@Service
public interface ReservationService {
    Reservation bookReservation(ReservationRequest request);

    ReservationResponse findReservationById(Long id);

    Set<Reservation> findReservationByPeriodWithNative(Instant start, Instant end);
    void deletedReservation(Long id);
    public Set<Reservation> findReservationsByUserId (Long userId);
    public Set<Reservation> findReservationsByCarId (Long carId);
    ReservationResponse updatedReservatedCar(Long id, ReservationCarUpdateRequest updateRequest);
    ReservationResponse updatedDates(Long id, ReservationDatesUpdateRequest datesUpdateRequest);

}
