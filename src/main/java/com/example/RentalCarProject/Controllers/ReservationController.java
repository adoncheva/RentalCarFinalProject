package com.example.RentalCarProject.Controllers;

import com.example.RentalCarProject.Convertors.ReservationConverter;
import com.example.RentalCarProject.DTOs.*;
import com.example.RentalCarProject.Entities.Reservation;
import com.example.RentalCarProject.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationConverter reservationConverter;
    @PostMapping("/register")
    public ResponseEntity<Reservation> save(@RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.bookReservation(reservationRequest));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ReservationResponse> getById(@PathVariable("id") Long reservationId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationService.findReservationById(reservationId));
    }

    @GetMapping(path = "/updatePeriod")
    public ResponseEntity<Set<ReservationResponse>> getReservationByPeriod(@RequestParam("startDate") Instant startDate,
                                                                           @RequestParam("endDate")Instant endDate) {
        Set<ReservationResponse> reservationResponses = new HashSet<>();
        reservationService.findReservationByPeriodWithNative(startDate, endDate).forEach(
                reservation -> {
                    ReservationResponse reservationResponse = reservationConverter.toReservationResponse(reservation);
                    reservationResponses.add(reservationResponse);
                }
        );

        return ResponseEntity.status(HttpStatus.FOUND).body(reservationResponses);
    }
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<Set<ReservationResponseUser>> getByUserId(@PathVariable("id") Long userId) {
        Set<ReservationResponseUser> reservationResponses = new HashSet<>();
        reservationService.findReservationsByUserId(userId).forEach(
                reservation -> {
                    ReservationResponseUser reservationResponse = reservationConverter.toReservationResponseByUser(reservation);
                    reservationResponses.add(reservationResponse);
                }
        );
        return ResponseEntity.status(HttpStatus.FOUND).body(reservationResponses);
    }
        @GetMapping(path = "/car/{id}")
    public ResponseEntity<Set<ReservationResponseByCar>> getByCarId(@PathVariable("id") Long carId) {
        Set<ReservationResponseByCar> responseByCarSet = new HashSet<>();
        reservationService.findReservationsByCarId(carId).forEach(
                reservation -> {
                    ReservationResponseByCar responseByCar = reservationConverter.toReservationResponseByCar(reservation);
                    responseByCarSet.add(responseByCar);
                }
        );

        return ResponseEntity.status(HttpStatus.FOUND).body(responseByCarSet);
    }
        @DeleteMapping(path ="/delete/{id}")
        public ResponseEntity<String> deletedReservation(@PathVariable Long id){
            reservationService.deletedReservation(id);
            String response = String.format("Reservation with %s id was deleted",id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        }
    @PutMapping(path="/update/{id}")
    public ResponseEntity<ReservationResponse> updateReservationCar(@PathVariable Long id, @RequestBody ReservationCarUpdateRequest carUpdateRequest ){

        ReservationResponse reservationResponse=reservationService.updatedReservatedCar(id,carUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(reservationResponse);
    }

    @PutMapping(path="/updateDates/{id}")
    public ResponseEntity<ReservationResponse> updateReservationDates(@PathVariable Long id, @RequestBody ReservationDatesUpdateRequest datesUpdateRequest ){

        ReservationResponse reservationResponse=reservationService.updatedDates(id,datesUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(reservationResponse);
    }
    }

