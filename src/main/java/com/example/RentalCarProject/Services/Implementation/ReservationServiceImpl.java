package com.example.RentalCarProject.Services.Implementation;

import com.example.RentalCarProject.Convertors.CarConverter;
import com.example.RentalCarProject.Convertors.ReservationConverter;
import com.example.RentalCarProject.DTOs.ReservationCarUpdateRequest;
import com.example.RentalCarProject.DTOs.ReservationDatesUpdateRequest;
import com.example.RentalCarProject.DTOs.ReservationRequest;
import com.example.RentalCarProject.DTOs.ReservationResponse;
import com.example.RentalCarProject.Entities.Car;
import com.example.RentalCarProject.Entities.Reservation;
import com.example.RentalCarProject.Repositories.CarRepository;
import com.example.RentalCarProject.Repositories.ReservationRepository;
import com.example.RentalCarProject.Services.ReservationService;
import com.example.RentalCarProject.util.DateFormatterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;
@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationConverter reservationConverter;
    private final CarRepository carRepository;
    private final CarConverter carConverter;
    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationConverter reservationConverter, CarRepository carRepository, CarConverter carConverter) {
        this.reservationRepository = reservationRepository;
        this.reservationConverter = reservationConverter;
        this.carRepository = carRepository;
        this.carConverter = carConverter;
    }

   @Override
    public Reservation bookReservation(ReservationRequest request) {
        return reservationRepository.save(reservationConverter.toReservation(request));
    }

    @Override
    public ReservationResponse findReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow();
        return reservationConverter.toReservationResponse(reservation);
    }

    @Override
    public Set<Reservation> findReservationByPeriodWithNative(Instant startDate, Instant endDate) {
        return reservationRepository.getReservationsByIntervalWithNative(
                        DateFormatterUtil.getDateFromDateTime(startDate).toString(),
                        DateFormatterUtil.getDateFromDateTime(endDate).toString())
                .orElse(Collections.emptySet());
    }
    public Set<Reservation> findReservationsByUserId (Long userId){
       return reservationRepository.findReservationsByUserId(userId).orElse(Collections.emptySet());
    }
    public Set<Reservation> findReservationsByCarId (Long carId){
       return reservationRepository.findReservationsByCarId(carId).orElse(Collections.emptySet());
    }

    @Override
    public ReservationResponse updatedReservatedCar(Long id, ReservationCarUpdateRequest updateRequest) {
        Reservation reservation=reservationRepository.findById(id).orElseThrow();
        if (updateRequest.getCarId()!= null && updateRequest.getCarId()!=0) {
            Car car=carRepository.findById(updateRequest.getCarId()).orElseThrow();
            reservation.setCar(car);
        }
        return reservationConverter.toReservationResponse(reservationRepository.save(reservation));
    }

    @Override
    public ReservationResponse updatedDates(Long id, ReservationDatesUpdateRequest datesUpdateRequest) {
        Reservation reservation=reservationRepository.findById(id).orElseThrow();
        if (datesUpdateRequest.getStartDate()!= null && !datesUpdateRequest.getStartDate().equals(0)) {

            reservation.setStartDate(datesUpdateRequest.getStartDate());
        }
            if (datesUpdateRequest.getEndDate()!= null && !datesUpdateRequest.getEndDate().equals(0)) {

                reservation.setEndDate(datesUpdateRequest.getEndDate());
            }
        return reservationConverter.toReservationResponse(reservationRepository.save(reservation));
    }


    @Override
    public void deletedReservation(Long id) {
        reservationRepository.deleteById(id);
    }

}
