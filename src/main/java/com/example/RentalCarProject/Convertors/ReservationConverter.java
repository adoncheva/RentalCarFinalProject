package com.example.RentalCarProject.Convertors;

import com.example.RentalCarProject.DTOs.ReservationRequest;
import com.example.RentalCarProject.DTOs.ReservationResponse;
import com.example.RentalCarProject.DTOs.ReservationResponseByCar;
import com.example.RentalCarProject.DTOs.ReservationResponseUser;
import com.example.RentalCarProject.Entities.Car;
import com.example.RentalCarProject.Entities.Reservation;
import com.example.RentalCarProject.Entities.User;
import com.example.RentalCarProject.Repositories.CarRepository;
import com.example.RentalCarProject.Repositories.UserRepository;

import com.example.RentalCarProject.util.DateFormatterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Component
public class ReservationConverter {

    @Autowired
    CarRepository carRepository;
    @Autowired
    UserRepository userRepository;


    public Reservation toReservation(ReservationRequest request) {

        return Reservation.builder()
                .user(getUser(request.getUserId()))
                .car(getCar(request.getCarId()))
                .startDate(Instant.parse(request.getStartDate().toString()))
                .endDate(Instant.parse(request.getEndDate().toString()))
                .price(getPeriod(request.getStartDate(),request.getEndDate()) * getCar(request.getCarId()).getPricePerDay())
                .build();
    }

    public ReservationResponse toReservationResponse(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .user(reservation.getUser())
                .car(reservation.getCar())
                .period(getPeriod(reservation.getEndDate(),reservation.getStartDate()))
                .price(reservation.getPrice())
                .startDate(DateFormatterUtil.getDateFromDateTime(reservation.getStartDate()).toString())
                .endDate(DateFormatterUtil.getDateFromDateTime(reservation.getEndDate()).toString())
                .build();
    }
    public ReservationResponseUser toReservationResponseByUser(Reservation reservation){
        return ReservationResponseUser.builder()
                .id(reservation.getId())
                .car(reservation.getCar())
                .period(getPeriod(reservation.getEndDate(),reservation.getStartDate()))
                .price(reservation.getPrice())
                .startDate(reservation.getStartDate().toString())
                .endDate(reservation.getEndDate().toString())
                .build();
    }public ReservationResponseByCar toReservationResponseByCar(Reservation reservation){
        return ReservationResponseByCar.builder()
                .id(reservation.getId())
                .user(reservation.getUser())
                .period(getPeriod(reservation.getEndDate(),reservation.getStartDate()))
                .price(reservation.getPrice())
                .startDate(reservation.getStartDate().toString())
                .endDate(reservation.getEndDate().toString())
                .build();
    }
    User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    Car getCar(Long id){
        return  carRepository.findById(id).orElseThrow();
    }
    Integer getPeriod (Instant startDay, Instant endDay){
        return Math.toIntExact(ChronoUnit.DAYS.between(startDay, endDay));
    }
}
