package com.example.RentalCarProject.Convertors;

import com.example.RentalCarProject.DTOs.CarRequest;
import com.example.RentalCarProject.DTOs.CarResponse;
import com.example.RentalCarProject.DTOs.CarUpdateResponse;
import com.example.RentalCarProject.Entities.Car;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {

    public Car toCar (CarRequest request){


        return Car.builder()
                .brand(request.getBrand())
                .model(request.getModel())
                .numberOfSeats(request.getNumberOfSeats())
                .pricePerDay(request.getPricePerDay())
                .build();
    }

    public CarResponse toCarResponse(Car car){

        return new CarResponse(car.getId(), car.getBrand(),
                car.getModel(), car.getNumberOfSeats(), car.getPricePerDay());
    }
    public CarUpdateResponse toCarUpdateResponse(Car car){

        return new CarUpdateResponse(car.getId(), car.getBrand(),
                car.getModel(), car.getNumberOfSeats(), car.getPricePerDay());
    }

}
