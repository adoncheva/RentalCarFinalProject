package com.example.RentalCarProject.Repositories;

import com.example.RentalCarProject.Entities.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM reservations WHERE start_date between ?1 and ?2 AND end_date between ?1 and ?2")
    Optional<Set<Reservation>> getReservationsByIntervalWithNative(String startDate, String endDate);
    Optional<Set<Reservation>> findReservationsByUserId(Long userId);
    Optional<Set<Reservation>> findReservationsByCarId(Long carId);
}
